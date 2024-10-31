import React, { useEffect, useCallback } from 'react';
import axios from 'axios';
import { useForm } from 'react-hook-form';
import { Button, Container, Form } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useNavigate } from 'react-router-dom';

const DetailUserInfo = () => {
  const { register, handleSubmit, setValue } = useForm();
  const accessToken = localStorage.getItem("accesstoken");
  const navigate = useNavigate();

  const fetchUserData = useCallback(async () => {
    try {
      const response = await axios.get('http://localhost:8080/users', {
        headers: {
          "Content-Type": "application/json",
          "accesstoken": accessToken,
        }
      });

      const userData = response.data;
      setValue('uiId', userData.uiId);
      setValue('uiPassword', userData.uiPassword);
      setValue('uiNickname', userData.uiNickname);
      setValue('uiEmail', userData.uiEmail);
      setValue('uiAge', userData.uiAge);
      setValue('uiGender', userData.uiGender);
      setValue('uiHeight', userData.uiHeight);
    } catch (error) {
      console.error('사용자 정보를 가져오는 중 오류 발생:', error);
    }
  }, [accessToken, setValue]);


  const onSubmit = async (data) => {
    try {
      await axios.put('http://localhost:8080/users', data, {
        headers: {
          "accesstoken": accessToken,
        },
      });
      alert('정보가 수정되었습니다.');
      console.log(data);
    } catch (error) {
      console.error('사용자 정보를 수정하는 중 오류 발생:', error);
      alert('수정 중 문제가 발생했습니다.');
    }
  };

  const handleDeleteUser = async () => {
    if (window.confirm('확인을 누르면 회원 정보가 삭제됩니다.')) {
      try {
        await axios.delete('http://localhost:8080/users', {
          headers: {
            "accesstoken": accessToken,
          },
          withCredentials: true,
        });
        localStorage.clear();
        alert('그동안 이용해주셔서 감사합니다.');
        navigate('/main');
      } catch (error) {
        alert(error.response?.data?.message || '삭제 중 문제가 발생했습니다.');
      }
    }
  };

  useEffect(() => {
    fetchUserData();
  }, [fetchUserData]);

  const ChangePassword = () => {
    navigate('/userInfo/password')
}

  return (
    <Container className="mt-5">
      <h2>회원 정보 수정</h2>
      <Form onSubmit={handleSubmit(onSubmit)}>
        <Form.Group className="mb-3">
          <Form.Label>아이디</Form.Label>
          <Form.Control
            {...register('uiId')}
            readOnly
          />
        </Form.Group>
        <Button variant="danger" onClick={ChangePassword}>
          비밀번호 수정
        </Button>

        <Form.Group className="mb-3">
          <Form.Label>닉네임</Form.Label>
          <Form.Control
            type="text"
            {...register('uiNickname')}
          />
        </Form.Group>

        <Form.Group className="mb-3">
          <Form.Label>이메일</Form.Label>
          <Form.Control
            type="email"
            {...register('uiEmail')}
          />
        </Form.Group>

        <Form.Group className="mb-3">
          <Form.Label>나이</Form.Label>
          <Form.Control
            type="number"
            {...register('uiAge')}
          />
        </Form.Group>

        <Form.Group className="mb-3">
          <Form.Label>성별</Form.Label>
          <Form.Control
            type="text"
            {...register('uiGender')}
          />
        </Form.Group>

        <Form.Group className="mb-3">
          <Form.Label>키 (cm)</Form.Label>
          <Form.Control
            type="number"
            {...register('uiHeight')}
          />
        </Form.Group>
        <Button type="submit" variant="primary" className="me-2">
          수정하기
        </Button>
        <Button variant="danger" onClick={handleDeleteUser}>
          회원탈퇴
        </Button>
      </Form>
    </Container>
  );
};

export default DetailUserInfo;
