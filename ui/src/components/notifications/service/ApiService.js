import axios from "axios";

const qs = require("querystring");

const config = {
  headers: {
    "Content-type": "application/json"
  }
};

export default new (class ApiService {
  async sendEmail(mailParams) {
    return await axios.post(
      "http://localhost:8080/api/notifications/send",
      JSON.stringify(mailParams),
      config
    );
  }

  async getMails() {
    return await axios.get(
      "http://localhost:8080/api/***/****", //getMailAdresses
      //   JSON.stringify(mailParams),
      config
    );
  }
})();
