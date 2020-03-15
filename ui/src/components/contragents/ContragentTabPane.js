import React from 'react';
import { Table, NavLink } from 'reactstrap';

  const renderContragentRowData = (contragent, contragentFieldNames) => {
    return contragentFieldNames.map(fieldName =>
      <td>{contragent[fieldName]}</td>
    );
  }

  const renderContragentsTableData = (contragents, contragentFieldNames, editContragentCallback) => {
    return contragents.map(contragent =>
      <tr>
        {renderContragentRowData(contragent, contragentFieldNames)}
        <td>
          <NavLink href="#" color="primary" onClick={() => editContragentCallback(contragent)}>
            Изменить
          </NavLink>
        </td>
      </tr>
    );
  }

  const renderContragentTableHeaders = (contragentFieldNamesInRus) => {
    return (
      <tr>
        {contragentFieldNamesInRus.map(contragentFieldName =>
          <th key={contragentFieldName}>
            {contragentFieldName}
          </th>)}
        <th></th>
      </tr>
    );
  }

  const renderContragentsData = (contragents, contragentFields, editContragentCallback) => {
    if (!contragents) return null;

    const contragentFieldNames = Object.keys(contragentFields);

    return renderContragentsTableData(contragents, contragentFieldNames, editContragentCallback);
  }

  const ContragentTabPane = ({contragents, contragentFields, editCallback}) => {
    return (
      <div>
        <Table striped>
          <thead>
            {renderContragentTableHeaders(Object.values(contragentFields))}
          </thead>
          <tbody>
            {renderContragentsData(contragents, contragentFields, editCallback)}
          </tbody>
        </Table>
      </div>
    )
  }

  export default ContragentTabPane;
