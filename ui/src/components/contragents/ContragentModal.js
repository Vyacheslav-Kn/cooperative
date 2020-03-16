import React from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter, InputGroup, Input } from 'reactstrap';

  const ContragentModal = ({currentContragentModal, currentContragent, inputChangeCallback}) => {
    if (currentContragentModal === null) return null;

    const {title, isOpen, contragentFields, toggleCallback, operationCallback} = currentContragentModal;

    const contragentFieldNames = Object.keys(contragentFields);

    return (
      <Modal isOpen={isOpen} toggle={toggleCallback}>
        <ModalHeader toggle={toggleCallback}>{title}</ModalHeader>
        <ModalBody>
          {
            contragentFieldNames.map(fieldName =>
              <InputGroup>
                <Input placeholder={contragentFields[fieldName]}
                       key={fieldName}
                       name={fieldName}
                       value={currentContragent ? currentContragent[fieldName] : ''}
                       onChange={inputChangeCallback} />
              </InputGroup>
          )}
        </ModalBody>
        <ModalFooter>
          <Button color="primary" onClick={operationCallback}>
            {currentContragent ? 'Сохранить' : 'Добавить'}
          </Button>
          <Button color="secondary" onClick={toggleCallback}>Закрыть</Button>
        </ModalFooter>
      </Modal>
    )
}

export default ContragentModal;
