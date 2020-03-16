import * as React from "react";
import { Button, FormGroup } from "react-bootstrap";
import { Container, Modal, ModalFooter } from "reactstrap";
import Input from "reactstrap/es/Input";
import Form from "react-bootstrap/Form";
import ApiService from "../service/ApiService";
import ModalHeader from "reactstrap/es/ModalHeader";
import ModalBody from "reactstrap/es/ModalBody";
import Select from "react-select";

export default class MailSender extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      mailParams: {
        receivers: [],
        subject: "",
        text: "",
        sendingType: "EMAIL",
        index: "",
        files: {}
      },
      showModal: false,
      selected: null,
      // emails: []
      emails: [
        {
          value: "evgeny.koenevega@gmail.com",
          label: "evgeny"
        },
        { value: "ascasjnaks@asv.tu", label: "qwe" },
        { value: "hhhq@a.o", label: "asdfz" },
        { value: "ascask@dbfn.qvm", label: "sssasc" }
      ]
    };

    this.handleChange = this.handleChange.bind(this);
    this.sendMail = this.sendMail.bind(this);
  }

  componentDidMount = () => {
    // this.getMailAdresses();
  };

  getMailAdresses = () => {
    ApiService.getMails().then(response => {
      if (response.status == 200) {
        const emails = response.data;
        /*     */
        this.setState({ emails });
      }
    });
  };

  handleChangeSelect = selected => {
    this.setState(state => ({
      mailParams: {
        ...state.mailParams,
        receivers: selected
      }
    }));
  };

  onFileUploaded = event => {
    const files = event.target.files;
    this.setState(state => ({
      mailParams: {
        ...state.mailParams,
        files
      }
    }));
  };

  handleChange({ target }) {
    this.setState(prevState => ({
      mailParams: {
        ...prevState.mailParams,
        [target.name]: target.value
      }
    }));
  }

  addReceivers = () => {
    this.setState(state => ({
      mailParams: {
        ...state.mailParams,
        receivers: this.state.emails
      }
    }));
  };

  sendMail() {
    let r = this.state.mailParams.receivers;
    var receivers = "";
    r.forEach(element => {
      receivers += element.value + ", ";
    });
    let params = this.state.mailParams;
    console.log(receivers);
    params.receivers = receivers;
    ApiService.sendEmail(params).then(response => {
      if (response.status == 200) {
        this.setState(prevState => ({
          mailParams: {
            ...prevState.mailParams,
            receivers: "",
            subject: "",
            text: "",
            index: ""
          },
          showModal: true,
          files: []
        }));
      }
    });
  }

  render() {
    const { receivers } = this.state.mailParams;
    console.log(this.state);
    return (
      <Container className="col-md-4">
        <h2>Нотификации</h2>
        <Form>
          <h3>Отправить сообщение</h3>
          <FormGroup>
            <label htmlFor="receivers">Эл.адрес(a) получателя(лей)</label>
            <Button
              style={{ margin: "15px" }}
              type="button"
              onClick={this.addReceivers}
            >
              Всех из списка
            </Button>
            <Select
              isMulti
              placeholder="Emails"
              value={receivers}
              onChange={this.handleChangeSelect}
              options={this.state.emails}
            />
          </FormGroup>
          <FormGroup>
            <label htmlFor="subject">Тема</label>
            <Input
              placeholder="Тема письма"
              name="subject"
              className="subject"
              value={this.state.mailParams.subject}
              onChange={this.handleChange}
            />
          </FormGroup>
          <FormGroup>
            <label htmlFor="emailText">Тело сообщения</label>
            <textarea
              name="text"
              placeholder="Содержимое письма"
              className="form-control text"
              value={this.state.mailParams.text}
              onChange={this.handleChange}
            />
          </FormGroup>
          <label htmlFor="files">Прикрепить файл</label>
          <input
            ref={r => (this.fileRef = r)}
            multiple
            style={{ margin: "15px" }}
            onChange={this.onFileUploaded}
            type="file"
          />
          <FormGroup>
            <label htmlFor="sendingType">Тип сообщения</label>
            <select
              name="sendingType"
              className="form-control sendingType"
              value={this.state.mailParams.sendingType}
              onChange={this.handleChange}
            >
              <option value="EMAIL" selected="selected">
                Электронное
              </option>
              <option value="SIMPLE">Бумажное</option>
            </select>
          </FormGroup>
          <FormGroup>
            <label htmlFor="index">Индекс</label>
            <Input
              name="index"
              className="index"
              value={this.state.mailParams.index}
              onChange={this.handleChange}
            />
          </FormGroup>
          <Button type="button" onClick={this.sendMail}>
            Отправить
          </Button>
        </Form>
        <Modal isOpen={this.state.showModal}>
          <ModalHeader toggle={() => this.setState({ showModal: false })}>
            Уведомление
          </ModalHeader>
          <ModalBody>
            <h4>Сообщение отправлено</h4>
          </ModalBody>
          <ModalFooter></ModalFooter>
        </Modal>
      </Container>
    );
  }
}
