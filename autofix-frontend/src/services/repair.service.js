import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/api/v1/repairs/')
}

const getByCode = code => {
    return httpClient.get(`/api/v1/repairs/code_${code}`)
}

const getById = id => {
    return httpClient.get(`/api/v1/repairs/${id}`)
}

const create = data => {
    return httpClient.post('/api/v1/repairs/', data)
}

const update = data => {
    return httpClient.put('/api/v1/repairs/', data)
}

const remove = id => {
    return httpClient.delete(`/api/v1/repairs/${id}`)
}

export default { getAll, getByCode, getById, create, update, remove }