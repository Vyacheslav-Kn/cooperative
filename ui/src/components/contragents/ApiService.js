import axios from 'axios'

const config = {
    headers: {
        'Content-type': 'application/json'
    }
}

const contragentTypes = {
    SERVICE_ORGANIZATION: 'SERVICE_ORGANIZATION',
    SUPPLIER: 'SUPPLIER',
    OTHER: 'OTHER'
}

const addTypeFieldToContragent = (contragent, type) => {
    const updatedContragent = {
        ...contragent,
        type: type
    }

    return updatedContragent;
}

export default new class ApiService {
    urlForAuthority = 'http://localhost:8080/api/contractors/authority';
    commonUrlForAllOtherContragetTypes = 'http://localhost:8080/api/contractors/others';

    getAuthorities() {
        return axios.get(this.urlForAuthority)
    }

    getServiceOrganizations() {
        return axios.get(`${this.commonUrlForAllOtherContragetTypes}?type=SERVICE_ORGANIZATION`)
    }

    getProviders() {
        return axios.get(`${this.commonUrlForAllOtherContragetTypes}?type=SUPPLIER`)
    }

    getOtherContragents() {
        return axios.get(`${this.commonUrlForAllOtherContragetTypes}?type=OTHER`)
    }

    updateAuthority(authority) {
        return axios.put(`${this.urlForAuthority}/${authority.id}`, JSON.stringify(authority), config)
    }

    updateServiceOrganization(serviceOrganization) {
        return axios.put(`${this.commonUrlForAllOtherContragetTypes}/${serviceOrganization.id}`, JSON.stringify(serviceOrganization), config)
    }

    updateProvider(provider) {
        return axios.put(`${this.commonUrlForAllOtherContragetTypes}/${provider.id}`, JSON.stringify(provider), config)
    }

    updateOtherContragent(otherContragent) {
        return axios.put(`${this.commonUrlForAllOtherContragetTypes}/${otherContragent.id}`, JSON.stringify(otherContragent), config)
    }

    addAuthority(authority) {
        return axios.post(`${this.urlForAuthority}`, JSON.stringify(authority), config)
    }

    addServiceOrganization(serviceOrganization) {
        const updatedContragent = addTypeFieldToContragent(serviceOrganization, contragentTypes.SERVICE_ORGANIZATION);

        return axios.post(`${this.commonUrlForAllOtherContragetTypes}`, JSON.stringify(updatedContragent), config)
    }

    addProvider(provider) {
        const updatedContragent = addTypeFieldToContragent(provider, contragentTypes.SUPPLIER);

        return axios.post(`${this.commonUrlForAllOtherContragetTypes}`, JSON.stringify(updatedContragent), config)
    }

    addOtherContragent(otherContragent) {
        const updatedContragent = addTypeFieldToContragent(otherContragent, contragentTypes.OTHER);

        return axios.post(`${this.commonUrlForAllOtherContragetTypes}`, JSON.stringify(updatedContragent), config)
    }
}
