const AuthorityFields = {
    fullName: 'Название',
    juridicalAccount: 'Юр. счет',
    postAddress: 'Почтовый адрес',
    headName: 'ФИО руководителя',
    headPost: 'Должность руководителя',
    registrationDate: 'Дата выдачи свидетельств',
    description: 'Общие данные'
};

let ServiceOrganizationFields, ProviderFields, OtherContragentFields;
ServiceOrganizationFields = ProviderFields = OtherContragentFields = {
    fullName: 'Название',
    juridicalAccount: 'Юр. счет',
    paymentAccount: 'Р/с',
    postAddress: 'Почтовый адрес',
    headName: 'ФИО руководителя',
    headPost: 'Должность руководителя',
    registrationDate: 'Дата выдачи свидетельств',
    description: 'Общие данные'
};

export {AuthorityFields, ServiceOrganizationFields, ProviderFields, OtherContragentFields};
