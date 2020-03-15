import React from 'react';
import { InputGroup, Input } from 'reactstrap';

  const SearchPanel = ({searchValue, changeCallback}) => {
    return (
        <InputGroup>
          <Input placeholder="Фильтр" value={searchValue} onChange={changeCallback} />
        </InputGroup>
    )
  }

  export default SearchPanel;
