import React from 'react';
import ReactDOM from 'react-dom/client';
import reportWebVitals from './reportWebVitals';
import Test from './Test';
import 'bootstrap/dist/css/bootstrap.min.css';
import Login from './assets/Login';
import Join from './assets/Join';
import Secession from './assets/Secession';
import UserInfo from './assets/UserInfo';
const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
   <UserInfo></UserInfo>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
