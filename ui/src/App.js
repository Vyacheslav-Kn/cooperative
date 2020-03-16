import React from "react";
import { Switch, Route, BrowserRouter } from "react-router-dom";
import "./App.css";
import { Register } from "./components/register/Register";
import "bootstrap/dist/css/bootstrap.css";
import CommonNavbar from "./common/Navbar";
import EnergyTableContainer from "./energyResources/energyTableContainer.js";
import NewMeter from "./energyResources/newMeter/newMeter.js";
import NewRecord from "./energyResources/newRecord.js";
import MailSender from "./components/notifications/components/MailSender";
import { Organization } from "./components/requisites/Organization";
import { Supervisor } from "./components/requisites/Supervisor";
import { StateRegistrationOfLegal } from "./components/requisites/StateRegistrationOfLegal";
import { SubjectHistory } from "./components/requisites/SubjectHistory";
import { SubjectHistoryEvent } from "./components/requisites/SubjectHistoryEvent";
import ContragentsContainer from './components/contragents/ContragentsContainer';

function App() {
  return (
    <BrowserRouter>
      <CommonNavbar />
      <Switch>
        <Route path="/register" component={Register} />
        <Route exact path="/" component={EnergyTableContainer} />
        <Route exact path="/meters/new" component={NewMeter} />
        <Route exact path="/meters/update" component={NewRecord} />
        <Route exact path="/requisites/supervisor" component={Supervisor} />
        <Route exact path="/requisites" component={Organization} />
        <Route
          exact
          path="/requisites/stateRegistrationOfLegal"
          component={StateRegistrationOfLegal}
        />
        <Route
          exact
          path="/requisites/subjectHistory"
          component={SubjectHistory}
        />
        <Route
          exact
          path="/requisites/subjectHistory/:id"
          component={SubjectHistoryEvent}
        />
        <Route path="/notifications" component={MailSender} />
        <Route path='/contragents' component={ContragentsContainer}/>
      </Switch>
    </BrowserRouter>
  );
}

export default App;
