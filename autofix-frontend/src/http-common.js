import axios from "axios";

//const autofixBackendServer = import.meta.env.VITE_PAYROLL_BACKEND_SERVER;
//const autofixBackendPort = import.meta.env.VITE_PAYROLL_BACKEND_PORT;

//console.log(autofixBackendServer)
//console.log(autofixBackendPort)

export default axios.create({
    baseURL: `http://autofix-app-bcaices.brazilsouth.cloudapp.azure.com`,
    headers: {
        'Content-Type': 'application/json'
    }
});