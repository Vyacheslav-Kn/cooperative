import api from '../../../api/api'

export default {

    uploadFile(file) {
        let formData = new FormData()
        formData.append('file', file)
        return api().post('/restore', formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        });
    }

}