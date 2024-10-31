import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import UserInput from '../components/UserInput';
import UserButton from '../components/UserButton';
import '../components/Join.css';

const Join = () => {

  const [userInfo, setUserInfo] = useState({
    uiId: '',
    uiPassword: '',
    uiPasswordConfirm: '',
    uiEmail: '',
    uiNickname: '',
    uiAge: '',
    uiGender: '',
  });

  const handleInputChange = event => {
    const { name, value } = event.target;
    setUserInfo(userInfo => ({
      ...userInfo,
      [name]: value,
    }));
  };

  const isVaild =
    userInfo.uiId &&
    userInfo.uiPassword.length >= 10 &&
    userInfo.uiPassword === userInfo.uiPasswordConfirm;

  const moveNavigate = useNavigate();
  let nickCheck = false;

  const processJoin = () => {
    fetch('http://localhost:8080/join', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json;charset=utf-8',
      },
      body: JSON.stringify({
        uiId: userInfo.uiId,
        uiPassword: userInfo.uiPassword,
        uiEmail: userInfo.uiEmail,
        uiNickname: userInfo.uiNickname,
        uiAge: userInfo.uiAge,
        uiGender: userInfo.uiGender,
      }),
    })
      .then(response => {
        if (response.ok) {
          return response.json();
        }
        throw new Error('서버 응답이 올바르지 않습니다.');
      })
      .then(data => {
        if (data === 1) {
          moveNavigate('/mainLogin');
        } else if (data === -1) {
          alert('이미 존재하는 아이디입니다.');
        } else if (data === -2) {
          alert('이미 존재하는 닉네임입니다.');
        } else if (data === -3) {
          alert('이미 존재하는 이메일입니다.');
        } else {
          alert('회원가입에 실패했습니다. 다시 시도해주세요.');
        }
      })
      .catch(error => {
        alert('회원가입 중 오류가 발생했습니다. 다시 시도해주세요.');
        console.error(error);
      });
  };


  return (
    <div className="Join">
      <div className="registrationFrame" onChange={handleInputChange}>
        <div className="backButtonFrame">
        </div>
        <h1 className="titleText">회원가입</h1>
        <div className="infoTextFrame">
          <p className="userinfoText">기본 정보</p>
          <p className="infoOptionalText">필수 사항</p>
        </div>
        <p>아이디</p>
        <div className="userInputFrame">
          <UserInput
            type="text"
            placeholder="아이디"
            value={userInfo.uiId}
            name="uiId"
          />
          <p>비밀번호</p>
          <UserInput
            type="password"
            placeholder="비밀번호"
            value={userInfo.uiPassword}
            name="uiPassword"
          />
          <p>비밀번호 확인</p>
          <UserInput
            type="password"
            placeholder="비밀번호 확인"
            value={userInfo.uiPasswordConfirm}
            name="uiPasswordConfirm"
          />
          <p>이메일</p>
          <UserInput
            type="email"
            placeholder="이메일"
            value={userInfo.uiEmail}
            name="uiEmail"
          />
          <p>닉네임</p>
          <UserInput
            type="password"
            placeholder="닉네임"
            value={userInfo.uiNickname}
            name="uiNickname"
          />
        </div>
        <div className="etcUserFrame">
          <div className="infoTextFrame">
            <p className="infoOptionalText">선택 사항</p>
          </div>
          <p>나이</p>
          <input
            className="uiAgeInput"
            type="text"
            placeholder="나이"
            value={userInfo.uiAge}
            name="uiAge"
          />
          <div>
            <label style={{ margin: 10 }}>
              성별:
              <select style={{ margin: 10 }} value={userInfo.uiGender} name="uiGender">
                <option value="남">남</option>
                <option value="여">여</option>
              </select>
            </label>
          </div>
          <div className="signupButtonFrame">
            <UserButton
              disabled={!isVaild}
              onClick={processJoin}
              text="회원 가입"
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Join;