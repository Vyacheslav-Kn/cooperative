import React, { useState, useEffect } from 'react'
import { Table } from 'reactstrap'
import {
  TabContent,
  TabPane,
  Nav,
  NavItem,
  NavLink,
  Button,
  Row,
  Alert,
  Col,
  CustomInput,
  FormGroup,
  Label
} from 'reactstrap'

import { Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap'
import classnames from 'classnames'
import { InputGroup, InputGroupAddon, InputGroupText, Input } from 'reactstrap'
import RegisterService from './services/RegisterService'
import PropertiesService from './services/PropertiesService'
import TrustService from "./services/TrustService";
import PersonService from "./services/PersonService";
import UploadService from "./services/UploadService";

export const Register = () => {

  const [property, setProperty] = useState({});

  const [ownersFilter, setOwnersFilter] = useState('')
  const [propertiesFilter, setPropertiesFilter] = useState('')
  const [trustsFilter, setTrustsFilter] = useState('')
  const [owner, setOwner] = useState({passportData:{}});
  const [owners, setOwners] = useState([])
  const [persons, setPersons] = useState(new Map());
  const [person, setPerson] = useState('');
  const [trusts, setTrusts] = useState([])
  const [properties, setProperties] = useState([])
  const [activeTab, setActiveTab] = useState('1')
  const [propertyModal, setPropertyModal] = useState(false)
  const [ownerModal, setOwnerModal] = useState(false)
  const [visible, setVisible] = useState(false);
  const [alertTextUploadService, setText] = useState('Файл не валидный.');

  const togglePropertyModal = () => setPropertyModal(!propertyModal)
  const toggleOwnerModal = () => setOwnerModal(!ownerModal)

  const toggleTab = tab => {
    if (activeTab !== tab) setActiveTab(tab)
  }

  const renderTableData = () => {
    const filteredOwners = owners.length > 0 ? owners.filter(owner => owner.fio.includes(ownersFilter) || owner.properties.map(property => property.id)
      .toString().includes(ownersFilter)) : []
    return filteredOwners.map(owner =>
      <tr>
        <td>{owner ? owner.fio : ''}</td>
        <td>{owner ? owner.properties.map(property => property.inventoryNumber)
          .toString() : ''}</td>
      </tr>,
    )
  }

  const renderTrustJournalData = () => {
    const filteredTrusts = trusts.length > 0 ? trusts.filter(trust => trust.from.includes(trustsFilter)
        || trust.to.includes(trustsFilter) || trust.startDate.includes(trustsFilter) || trust.endDate.includes(trustsFilter)) : [];
    return filteredTrusts.map(trust =>
        <tr>
          <td>{trust.from}</td>
          <td>{trust.to}</td>
          <td>{trust.startDate}</td>
          <td>{trust.endDate}</td>
        </tr>,
    )
  }

  const renderRegisterTable = () => {
    return (
      <div>
        <InputGroup>
          <Input placeholder="Фильтр" value={ownersFilter} onChange={e => setOwnersFilter(e.target.value)}/>
        </InputGroup>
        <Table striped>
          <thead>
          <tr>
            <th>Собственник</th>
            <th>Недвижимости</th>
          </tr>
          </thead>
          <tbody>
          {renderTableData()}
          </tbody>
        </Table>
      </div>
    )
  }

  const renderPropertyTable = () => {
    return (
        <div>
          <InputGroup>
            <Input placeholder="Фильтр" value={propertiesFilter} onChange={e => setPropertiesFilter(e.target.value)}/>
          </InputGroup>
          <Table striped>
            <thead>
            <tr>
              <th>Инвентарный номер</th>
              <th>Площадь</th>
              <th>Номер помещения транспортного назначения</th>
            </tr>
            </thead>
            <tbody>
            {renderPropertyTableData()}
            </tbody>
            <Button color="primary" onClick={togglePropertyModal}>Добавить недвижимость</Button>
          </Table>
        </div>
    )
  }

  const renderOwnersTable = () => {
    return (
        <div>
          <InputGroup>
            <Input placeholder="Фильтр" value={ownersFilter} onChange={e => setOwnersFilter(e.target.value)}/>
          </InputGroup>
          <Table striped>
            <thead>
            <tr>
              <th>ФИО</th>
              <th>Моб. Телефон</th>
              <th>Почта</th>
              <th>Skype</th>
              <th>Адрес</th>
              <th>Паспортные данные</th>
              <th>Паспортный номер</th>
              <th>Кто выдал паспорт</th>
            </tr>
            </thead>
            <tbody>
            {renderOwnersTableData()}
            </tbody>
            <Button color="primary" onClick={toggleOwnerModal}>Добавить владельца</Button>
          </Table>
        </div>
    )
  }

  const renderUpload = () => {
    return (
        <div>
          <Alert color="primary" isOpen={visible} toggle={()=> setVisible(false)} fade={false}>
            {alertTextUploadService}
          </Alert>
          <FormGroup>
            <Label for="exampleCustomFileBrowser">CSV реестр</Label>
            <CustomInput type="file" id="registerBrowser" name="customFile" label="Выберите файл реестра."/>
          </FormGroup>
          <Button outline color="primary" onClick={() => upload()}>Обновить реестр</Button>
        </div>
    )
  };

  const upload = () => {
    setText('Файл не валидный.');
    setVisible(false);
    const upload = document.getElementById("registerBrowser");
    if (upload.files && upload.files.length === 1) {
      const file = upload.files[0];
      if (file.name.includes(".csv")) {
        const promise = UploadService.uploadFile(file);
        setText("Обработка...");
        setVisible(true);
        promise.then(() => {
          updateOwners();
          updatePersons();
          updateProperties();
          setVisible(false);
          setText("Успешно загружено.");
          setVisible(true);
        });
        promise.catch(() => {
          setText("Произошла ошибка.");
          setVisible(true);
        });
      } else {
        setVisible(true);
      }
    } else {
      setVisible(true);
    }
  };

  const renderPropertyTableData = () => {
    const filteredProperties = properties.length > 0 ? properties.filter(property => property.number.includes(propertiesFilter)
        || (property.square + '').includes(propertiesFilter) || (property.ptn && property.ptn.includes(propertiesFilter))) : [];
    return filteredProperties.map(property =>
        <tr>
          <td>{property.number}</td>
          <td>{property.square}</td>
          <td>{property.ptn}</td>
        </tr>,
    )
  }

  const renderOwnersTableData = () => {
    if (owners.length > 0) {
      const filteredOwners = owners.length > 0 ? owners.filter(owner => (owner.fio && owner.fio.includes(ownersFilter))
          || (owner.mobilePhone && owner.mobilePhone.includes(ownersFilter))
          || (owner.email && owner.email.includes(ownersFilter))
          || (owner.skype && owner.skype.includes(ownersFilter))
          || (owner.livingAddress && owner.livingAddress.includes(ownersFilter))
          || (owner.passportNumber && owner.passportNumber.includes(ownersFilter))
          || (owner.issuingAuthority && owner.issuingAuthority.includes(ownersFilter))
          || (owner.passportData && owner.passportData.includes(ownersFilter))) : [];
      return filteredOwners.map(owner =>
          <tr>
            <td>{owner ? owner.fio : ''}</td>
            {<td>{owner.mobilePhone}</td>}
            {<td>{owner.email}</td>}
            {<td>{owner.skype}</td>}
            {<td>{owner.livingAddress}</td>}
            <td>{owner ? owner.passportData : ''}</td>
            {<td>{owner.passportNumber}</td>}
            {<td>{owner.issuingAuthority}</td>}
          </tr>,
      )
    }
  }

  const renderPropertyModal = () => {
    return (
      <Modal isOpen={propertyModal} toggle={togglePropertyModal}>
        <ModalHeader toggle={togglePropertyModal}>Недвижимость</ModalHeader>
        <ModalBody>
          <InputGroup>
            <Input placeholder="Инвентарный номер" onChange={e => {property.inventoryNumber = e.target.value}} value={property.inventoryNumber}/>
          </InputGroup>
          <InputGroup>
            <Input placeholder="Площадь" onChange={e => {property.square = e.target.value}} value={property.square}/>
          </InputGroup>
          <InputGroup>
            <Input placeholder="Номер помещения транспортного назначения" onChange={e => {property.ptn = e.target.value}} value={property.ptn}/>
          </InputGroup>
          <form>
            <div className="form-group row">
              <div className="col-md-6">
                <label htmlFor="type" className="col-sm-2 col-form-label">ФИО владельца</label>
                <select className="form-control" id="type" onChange={(e) => addPerson(e)}>
                  {Array.from(persons, ([key, person]) => {
                    if (person) {
                      return <option
                        key={person.id}
                        value={person.id}>
                        {`${person.surname} ${person.name} ${person.lastName}`}</option>
                    }
                  })}
                </select>
              </div>
            </div>
          </form>
        </ModalBody>
        <ModalFooter>
          <Button color="primary" onClick={() => addPropertyAction(property)}>Добавить</Button>
          <Button color="secondary" onClick={togglePropertyModal}>Закрыть</Button>
        </ModalFooter>
      </Modal>
    )
  }

  const addPerson = (e) => {
    if (person) {
      person.id = e.target.value;
    } else {
      setPerson({id: e.target.value})
    }
  }

  const addPropertyAction = (property) => {
    property.owners = [{
      personId: person.id || persons.entries().next().value[0],
      owningPercent: 1
    }]
    addProperty(property).then(() => {
      togglePropertyModal();
      updateProperties();
      updateOwners();
    });
  }

  const renderOwnerModal = () => {
    return (
      <Modal isOpen={ownerModal} toggle={toggleOwnerModal}>
        <ModalHeader toggle={toggleOwnerModal}>Владелец</ModalHeader>
        <ModalBody>
          <InputGroup>
            <Input placeholder="Имя" value={owner.name} onChange={e => {owner.name = e.target.value}}/>
          </InputGroup>
          <InputGroup>
            <Input placeholder="Фамилия" value={owner.surname} onChange={e => {owner.surname= e.target.value}}/>
          </InputGroup>
          <InputGroup>
            <Input placeholder="Отчество" value={owner.lastName} onChange={e => {owner.lastName = e.target.value}}/>
          </InputGroup>
          <InputGroup>
            <Input placeholder="Мобильный телефон" value={owner.mobilePhone} onChange={e => {owner.mobilePhone = e.target.value}}/>
          </InputGroup>
          <InputGroup>
            <Input placeholder="Почта" value={owner.email} onChange={e => {owner.email = e.target.value}}/>
          </InputGroup>
          <InputGroup>
            <Input placeholder="Skype" value={owner.skype} onChange={e => {owner.skype = e.target.value}}/>
          </InputGroup>
          <InputGroup>
            <Input placeholder="Адрес" value={owner.livingAddress} onChange={e => {owner.livingAddress = e.target.value}}/>
          </InputGroup>
          <InputGroup>
            <Input placeholder="Номер паспорта" value={owner.passportData.number} onChange={e => {owner.passportData.number = e.target.value}}/>
          </InputGroup>
          <InputGroup>
            <Input placeholder="Данные паспорта" value={owner.passportData.data} onChange={e => {owner.passportData.data = e.target.value}}/>
          </InputGroup>
          <InputGroup>
            <Input placeholder="Кто выдал паспорт" value={owner.passportData.issuingAuthority} onChange={e => {owner.passportData.issuingAuthority = e.target.value}}/>
          </InputGroup>
          <InputGroup>
            <Input placeholder="Персональный номер паспорта" value={owner.passportData.personalNumber} onChange={e => {owner.passportData.personalNumber = e.target.value}}/>
          </InputGroup>
        </ModalBody>
        <ModalFooter>
          <Button color="primary" onClick={() => addOwnersAction(owner)}>Добавить</Button>
          <Button color="secondary" onClick={toggleOwnerModal}>Закрыть</Button>
        </ModalFooter>
      </Modal>
    )
  }

  const addOwnersAction = (owner) => {
    owner.documentType='паспорт гражданина РБ';
    console.log(owner);
    PersonService.addPerson(owner).then((ownerResponse) => {
      let promises = [];
      promises.push(RegisterService.addCommunications(ownerResponse.data.id, {personId: ownerResponse.data.id, communicationType: 'mobilePhone', communicationValue: owner.mobilePhone}));
      if (owner.email) {
        promises.push(RegisterService.addCommunications(ownerResponse.data.id, {
          personId: ownerResponse.data.id,
          communicationType: 'email',
          communicationValue: owner.email
        }));
      }
      if(owner.skype) {
        promises.push(RegisterService.addCommunications(ownerResponse.data.id, {
          personId: ownerResponse.data.id,
          communicationType: 'skype',
          communicationValue: owner.skype
        }));
      }
      Promise.all(promises).then(()=>{
        toggleOwnerModal();
        updateOwners();
        updatePersons();
        setOwner({passportData:{}});
      }).catch(()=>{
        toggleOwnerModal();
        updateOwners();
        updatePersons();
        setOwner({passportData:{}});
      });
    })
  }

  const updateOwners = () => {
    RegisterService.fetchOwners().then((value) => {
      value.data.forEach(owner => {
        RegisterService.fetchOwnerCommunications(owner.personEntity.id)
            .then((communications) => {
              let communicationObject = {};
              communications.data.forEach((communication) => {
                communicationObject[communication.communicationType] = communication.communicationValue;
              })
              owners.push(
                  {
                    ...communicationObject,
                    fio: `${owner.personEntity.surname} ${owner.personEntity.name} ${owner.personEntity.lastName}`,
                    passportData: owner.personEntity.documentType === 'паспорт гражданина РБ' && owner.personEntity.passportData ? owner.personEntity.passportData.number : 'вид на жительство РБ',
                    properties: owner.entities,
                    issuingAuthority: owner.personEntity.documentType === 'паспорт гражданина РБ' && owner.personEntity.passportData ? owner.personEntity.passportData.issuingAuthority : '',
                    passportNumber: owner.personEntity.documentType === 'паспорт гражданина РБ' && owner.personEntity.passportData ? owner.personEntity.passportData.number : '',
                    id: owner.personEntity.id,
                  }
              )
              setOwners([...owners])
            })
      })
    })
  }

  const updatePersons = () => {
    PersonService.fetchPersons().then((value) => {
      value.data.forEach(person => {
        persons.set(person.id, person);
      });
      TrustService.fetchTrusts().then((value) => {
        value.data.forEach(trust => {
          const from = persons.get(trust.proprietorId);
          const to = persons.get(trust.personId);
          if (from && to) {
            setTrusts([...trusts, {
              from: `${from.surname} ${from.name} ${from.lastName}`,
              to: `${to.surname} ${to.name} ${to.lastName}`,
              startDate: new Date(trust.startDate * 1000).toISOString().substring(0, 10),
              endDate: new Date(trust.startDate * 1000 + trust.duration * 24 * 60 * 60 * 1000).toISOString().substring(0, 10)
            }]);
          }
        })
      })
    })
  };

  const updateProperties = () => {
    PropertiesService.fetchProperties().then((value) => {
      let cleanedProperties = value.data.map(property => {
        return {
          number: `${property.inventoryNumber}`,
          square: property.square,
          ptn: property.ptn,
          owners: property.owners,
          part: property.part,
          id: property.id,
        }
      })
      setProperties(cleanedProperties);
    })
  }

  const addProperty = (property) => {
    return PropertiesService.addProperty(property)
  }

  useEffect(() => updateOwners(), [])
  useEffect(() => updatePersons(), [])
  useEffect(() => updateProperties(), [])

  return (
    <Row style={{ width: '100%' }}>
      <Col xs="3">
        <Nav tabs vertical className="ml-3 mt-5">
          <NavItem>
            <NavLink
              className={classnames({ active: activeTab === '1' })}
              onClick={() => {
                toggleTab('1')
              }}
            >
              Реестр
            </NavLink>
          </NavItem>
          <NavItem>
            <NavLink
              className={classnames({ active: activeTab === '2' })}
              onClick={() => {
                toggleTab('2')
              }}
            >
              Журнал доверенностей
            </NavLink>
          </NavItem>
          <NavItem>
            <NavLink
              className={classnames({ active: activeTab === '3' })}
              onClick={() => {
                toggleTab('3')
              }}
            >
              Объекты недвижимости
            </NavLink>
          </NavItem>
          <NavItem>
            <NavLink
              className={classnames({ active: activeTab === '4' })}
              onClick={() => {
                toggleTab('4')
              }}
            >
              Собственники
            </NavLink>
          </NavItem>
          <NavItem>
            <NavLink
                className={classnames({ active: activeTab === '5' })}
                onClick={() => {
                  toggleTab('5')
                }}
            >
              Загрузка из файла
            </NavLink>
          </NavItem>
        </Nav>
      </Col>
      <Col xs="9">
        <TabContent activeTab={activeTab} className="ml-5 mt-5 mr-2">
          <TabPane tabId="2">
            <InputGroup>
              <Input placeholder="Фильтр" value={trustsFilter} onChange={e => setTrustsFilter(e.target.value)}/>
            </InputGroup>
            <Table striped>
              <thead>
              <tr>
                <th>От кого</th>
                <th>Кому</th>
                <th>Дата начала</th>
                <th>Дата окончания</th>
              </tr>
              </thead>
              <tbody>
              {renderTrustJournalData()}
              </tbody>
            </Table>
          </TabPane>
          <TabPane tabId="1">
            {renderRegisterTable()}
          </TabPane>
          <TabPane tabId="3">
            {renderPropertyTable()}
          </TabPane>
          <TabPane tabId="4">
            {renderOwnersTable()}
          </TabPane>
          <TabPane tabId="5">
            {renderUpload()}
          </TabPane>
        </TabContent>
      </Col>
      <div>
        {renderPropertyModal()}
        {renderOwnerModal()}
      </div>
    </Row>
  )
}
