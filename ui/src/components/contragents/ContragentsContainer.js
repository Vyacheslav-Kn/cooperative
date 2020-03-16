import React, {Component} from 'react';
import { TabContent, TabPane, Nav, NavItem, NavLink, Row, Col, Button } from 'reactstrap';
import classnames from 'classnames';
import ContragentTabPane from './ContragentTabPane';
import ContragentModal from './ContragentModal';
import SearchPanel from './SearchPanel';
import ApiService from './ApiService';
import * as constants from './Constants';

class ContragentsContainer extends Component {
  constructor(props) {
    super(props)

    this.state = {
      authorities: [],
      authoritiesFilter: '',
      serviceOrganizations: [],
      serviceOrganizationsFilter: '',
      providers: [],
      providersFilter: '',
      otherContragents: [],
      otherContragentsFilter: '',
      activeTab: '1',
      currentContragentModal: null,
      currentContragent: null
    }
  }

  inputChangeCallback = (event) => {
    const {currentContragent} = this.state;

    if (currentContragent) {
      this.setState({
        currentContragent: {
          ...currentContragent,
          [event.target.name]: event.target.value
        }
      });
    } else {
      this.setState({
        currentContragent: {
          [event.target.name]: event.target.value
        }
      });
    }
  }

  getUpdatedContragents = (contragents, newContragent) => {
    const updatedContragents = [...contragents];

    if (newContragent) {
      updatedContragents.push(newContragent);
    } else {
      const {currentContragent} = this.state;
      const index = contragents.findIndex(c => c.id === currentContragent.id);
      updatedContragents[index] = currentContragent;
    }

    return updatedContragents;
  }

    updateAuthoritiesState = (currentAuthorities, currentContragent) => {
      const updatedAuthorities = this.getUpdatedContragents(currentAuthorities, currentContragent);
      this.setState({
        authorities: updatedAuthorities,
        currentContragent: null,
        currentContragentModal: null
      });
    }

  updateAuthorities = () => {
    const {authorities} = this.state;
    let contragent = this.state.currentContragent;

    if (contragent.id) {
      ApiService.updateAuthority(contragent).then(({data}) => {
        contragent = null;
        this.updateAuthoritiesState(authorities, contragent);
      }).catch(ex => {
        console.log(ex) 
      });
    }
    else {
      ApiService.addAuthority(contragent).then(({data}) => {
        contragent = data;
        this.updateAuthoritiesState(authorities, contragent);
      }).catch(ex => {
        console.log(ex) 
      });
    }
  }

  updateServiceOrganizationsState = (currentServiceOrganizations, currentContragent) => {
    const updatedServiceOrganizations = this.getUpdatedContragents(currentServiceOrganizations, currentContragent);
    this.setState({
      serviceOrganizations: updatedServiceOrganizations,
      currentContragent: null,
      currentContragentModal: null
    });
  }

  updateServiceOrganizations = () => {
    const {serviceOrganizations} = this.state;
    let contragent = this.state.currentContragent;

    if (contragent.id) {
      ApiService.updateServiceOrganization(contragent).then(({data}) => {
        contragent = null;
        this.updateServiceOrganizationsState(serviceOrganizations, contragent);
      }).catch(ex => {
        console.log(ex) 
      });
    }
    else {
      ApiService.addServiceOrganization(contragent).then(({data}) => {
        contragent = data;
        this.updateServiceOrganizationsState(serviceOrganizations, contragent);
      }).catch(ex => {
        console.log(ex) 
      });
    }
  }

  updateProvidersState = (currentProviders, currentContragent) => {
    const updatedProviders = this.getUpdatedContragents(currentProviders, currentContragent);
    this.setState({
      providers: updatedProviders,
      currentContragent: null,
      currentContragentModal: null
    });
  }

  updateProviders = () => {
    const {providers} = this.state;
    let contragent = this.state.currentContragent;

    if (contragent.id) {
      ApiService.updateProvider(contragent).then(({data}) => {
        contragent = null;
        this.updateProvidersState(providers, contragent);
      }).catch(ex => {
        console.log(ex) 
      });
    }
    else {
      ApiService.addProvider(contragent).then(({data}) => {
        contragent = data;
        this.updateProvidersState(providers, contragent);
      }).catch(ex => {
        console.log(ex) 
      });
    }
  }

  updateOtherContragentsState = (currentOtherContragents, currentContragent) => {
    const updatedOtherContragents = this.getUpdatedContragents(currentOtherContragents, currentContragent);
    this.setState({
      otherContragents: updatedOtherContragents,
      currentContragent: null,
      currentContragentModal: null
    });
  }

  updateOtherContragents = () => {
    const {otherContragents} = this.state;
    let contragent = this.state.currentContragent;

    if (contragent.id) {
      ApiService.updateOtherContragent(contragent).then(({data}) => {
        contragent = null;
        this.updateOtherContragentsState(otherContragents, contragent);
      }).catch(ex => {
        console.log(ex) 
      });
    }
    else {
      ApiService.addOtherContragent(contragent).then(({data}) => {
        contragent = data;
        this.updateOtherContragentsState(otherContragents, contragent);
      }).catch(ex => {
        console.log(ex) 
      });
    }
  }

  toggleTab = tab => {
    const {activeTab} = this.state;

    if (activeTab !== tab) this.setState({activeTab: tab});
  }

  toggleModal = () => {
    this.setState({
      currentContragent: null,
      currentContragentModal: null
    });
  }

  authorityModal = {
    title: 'Орган власти',
    isOpen: true,
    toggleCallback: this.toggleModal,
    operationCallback: this.updateAuthorities,
    contragentFields: constants.AuthorityFields
  };

  serviceOrganizationModal = {
    title: 'Обслуживающая организация',
    isOpen: true,
    toggleCallback: this.toggleModal,
    operationCallback: this.updateServiceOrganizations,
    contragentFields: constants.ServiceOrganizationFields
  };

  providerModal = {
    title: 'Поставщик',
    isOpen: true,
    toggleCallback: this.toggleModal,
    operationCallback: this.updateProviders,
    contragentFields: constants.ProviderFields
  };

  otherContragentModal = {
    title: 'Контрагент',
    isOpen: true,
    toggleCallback: this.toggleModal,
    operationCallback: this.updateOtherContragents,
    contragentFields: constants.OtherContragentFields
  };

  setStateForAuthorityModal = authority => {
    this.setState({
      currentContragent: authority,
      currentContragentModal: this.authorityModal
    });
  }

  setStateForServiceOrganizationModal = serviceOrganization => {
    this.setState({
      currentContragent: serviceOrganization,
      currentContragentModal: this.serviceOrganizationModal
    });
  }

  setStateForProviderModal = provider => {
    this.setState({
      currentContragent: provider,
      currentContragentModal: this.providerModal
    });
  }

  setStateForOtherContragentModal = otherContragent => {
    this.setState({
      currentContragent: otherContragent,
      currentContragentModal: this.otherContragentModal
    });
  }

  filterContragents = (contragents, filterValue) => {
    if (!filterValue.length) {
      return contragents;
    }

    const filteredContragents = contragents.length > 0
      ? contragents.filter(contragent => contragent.fullName.includes(filterValue) ||
      contragent.registrationDate.includes(filterValue))
      : [];

      return filteredContragents;
  }

  setAuthoritiesFilter = (event) => {
    this.setState({
      authoritiesFilter: event.target.value
    });
  }

  setServiceOrganizationsFilter = (event) => {
    this.setState({
      serviceOrganizationsFilter: event.target.value
    });
  }

  setProvidersFilter = (event) => {
    this.setState({
      providersFilter: event.target.value
    });
  }

  setOtherContragentsFilter = (event) => {
    this.setState({
      otherContragentsFilter: event.target.value
    });
  }

  componentDidMount() {
    let authorities, serviceOrganizations, providers, otherContragents;

    ApiService.getAuthorities().then(({data}) => {
      authorities = data.content.length ? data.content : [];
      this.setState({authorities});
    }).catch(ex => {
      console.log(ex);
    });

    ApiService.getServiceOrganizations().then(({data}) => {
      serviceOrganizations = data.content.length ? data.content : [];
      this.setState({serviceOrganizations});
    }).catch(ex => {
      console.log(ex);
    });

    ApiService.getProviders().then(({data}) => {
      providers = data.content.length ? data.content : [];
      this.setState({providers});
    }).catch(ex => {
      console.log(ex);
    });

    ApiService.getOtherContragents().then(({data}) => {
      otherContragents = data.content.length ? data.content : [];
      this.setState({otherContragents});
    }).catch(ex => {
      console.log(ex);
    });
  }

  render() {
    const {
      authorities,
      authoritiesFilter,
      serviceOrganizations,
      serviceOrganizationsFilter,
      providers,
      providersFilter,
      otherContragents,
      otherContragentsFilter,
      currentContragent,
      currentContragentModal,
      activeTab
    } = this.state;

    const currentAuthorities = this.filterContragents(authorities, authoritiesFilter);
    const currentServiceOrganizations = this.filterContragents(serviceOrganizations, serviceOrganizationsFilter);
    const currentProviders = this.filterContragents(providers, providersFilter);
    const currentOtherContragents = this.filterContragents(otherContragents, otherContragentsFilter);

    return (
      <Row style={{width: '100%'}}>
        <Col xs="3">
          <Nav tabs vertical className="ml-3 mt-5">
            <NavItem>
              <NavLink
                className={classnames({active: activeTab === '1'})}
                onClick={() => {this.toggleTab('1');}}>
                Органы власти
              </NavLink>
            </NavItem>
            <NavItem>
              <NavLink
                className={classnames({active: activeTab === '2'})}
                onClick={() => {this.toggleTab('2');}}>
                Обслуживающие организации
              </NavLink>
            </NavItem>
            <NavItem>
              <NavLink
                className={classnames({active: activeTab === '3'})}
                onClick={() => {this.toggleTab('3');}}>
                Поставщики
              </NavLink>
            </NavItem>
            <NavItem>
              <NavLink
                className={classnames({active: activeTab === '4'})}
                onClick={() => {this.toggleTab('4');}}>
                Иные
              </NavLink>
            </NavItem>
          </Nav>
        </Col>
        <Col xs="9">
          <TabContent activeTab={activeTab} className="ml-5 mt-5 mr-2">
            <TabPane tabId="1">
              <SearchPanel searchValue={authoritiesFilter} changeCallback={this.setAuthoritiesFilter} />
              <ContragentTabPane contragents={currentAuthorities}
                                 contragentFields={constants.AuthorityFields}
                                 editCallback={this.setStateForAuthorityModal} />
              <Button color="primary" onClick={this.setStateForAuthorityModal}>Добавить</Button>
            </TabPane>
            <TabPane tabId="2">
              <SearchPanel searchValue={serviceOrganizationsFilter} changeCallback={this.setServiceOrganizationsFilter} />
              <ContragentTabPane contragents={currentServiceOrganizations}
                                 contragentFields={constants.ServiceOrganizationFields}
                                 editCallback={this.setStateForServiceOrganizationModal} />
              <Button color="primary" onClick={this.setStateForServiceOrganizationModal}>Добавить</Button>
            </TabPane>
            <TabPane tabId="3">
              <SearchPanel searchValue={providersFilter} changeCallback={this.setProvidersFilter} />
              <ContragentTabPane contragents={currentProviders}
                                 contragentFields={constants.ProviderFields}
                                 editCallback={this.setStateForProviderModal} />
              <Button color="primary" onClick={this.setStateForProviderModal}>Добавить</Button>
            </TabPane>
            <TabPane tabId="4">
              <SearchPanel searchValue={otherContragentsFilter} changeCallback={this.setOtherContragentsFilter} />
              <ContragentTabPane contragents={currentOtherContragents}
                                 contragentFields={constants.OtherContragentFields}
                                 editCallback={this.setStateForOtherContragentModal} />
              <Button color="primary" onClick={this.setStateForOtherContragentModal}>Добавить</Button>
            </TabPane>
          </TabContent>
        </Col>
        <ContragentModal currentContragentModal={currentContragentModal}
                         currentContragent={currentContragent}
                         inputChangeCallback={this.inputChangeCallback} />
      </Row>
    )
  }
}

export default ContragentsContainer;
