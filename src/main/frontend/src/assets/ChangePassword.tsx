import React, { useState } from 'react';
import { useForm, SubmitHandler } from 'react-hook-form';
import axios from 'axios';
import { Button, Container, Form, Alert } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useNavigate } from 'react-router-dom';

interface ChangePasswordForm {
  uiPassword: string;
  newUiPassword: string;
  confirmPassword: string;
}

const ChangePassword: React.FC = () => {
  const { register, handleSubmit, watch, formState: { errors } } = useForm<ChangePasswordForm>();
  const [errorMessage, setErrorMessage] = useState<string>('');
  const [successMessage, setSuccessMessage] = useState<string>('');
  const navigate = useNavigate();
  const accessToken = localStorage.getItem("accesstoken");

  const onSubmit: SubmitHandler<ChangePasswordForm> = async (data) => {
    try {
      await axios.patch('http://localhost:8080/users', {
        uiPassword: data.uiPassword,
        newUiPassword: data.newUiPassword,
      }, {
        headers: {
          "accesstoken": accessToken,
        },
      });
      setSuccessMessage('비밀번호가 성공적으로 변경되었습니다.');
      setErrorMessage('');
      navigate('/detailUserInfo');
    } catch (error) {
      setErrorMessage(error.response?.data?.message || '비밀번호 변경 중 문제가 발생했습니다.');
      setSuccessMessage('');
    }
  };

  return (
    <Container className="mt-5">
      <h2>비밀번호 변경</h2>
      {errorMessage && <Alert variant="danger">{errorMessage}</Alert>}
      {successMessage && <Alert variant="success">{successMessage}</Alert>}
      <Form onSubmit={handleSubmit(onSubmit)}>
        <Form.Group className="mb-3">
          <Form.Label>현재 비밀번호</Form.Label>
          <Form.Control
            type="password"
            {...register('uiPassword', { required: '현재 비밀번호를 입력하세요.' })}
            isInvalid={!!errors.uiPassword}
          />
          <Form.Control.Feedback type="invalid">
            {errors.uiPassword?.message}
          </Form.Control.Feedback>
        </Form.Group>

        <Form.Group className="mb-3">
          <Form.Label>새 비밀번호</Form.Label>
          <Form.Control
            type="password"
            {...register('newUiPassword', { 
              required: '새 비밀번호를 입력하세요.',
              pattern: {
                value: /^(?=.*[A-Za-z])(?=.*\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\d~!@#$%^&*()+|=]{8,16}$/,
                message: '비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.',
              },
            })}
            isInvalid={!!errors.newUiPassword}
          />
          <Form.Control.Feedback type="invalid">
            {errors.newUiPassword?.message}
          </Form.Control.Feedback>
        </Form.Group>

        <Form.Group className="mb-3">
          <Form.Label>새 비밀번호 확인</Form.Label>
          <Form.Control
            type="password"
            {...register('confirmPassword', {
              required: '비밀번호 확인이 필요합니다.',
              validate: (value) => value === watch('newUiPassword') || '새 비밀번호가 일치하지 않습니다.',
            })}
            isInvalid={!!errors.confirmPassword}
          />
          <Form.Control.Feedback type="invalid">
            {errors.confirmPassword?.message}
          </Form.Control.Feedback>
        </Form.Group>

        <Button type="submit" variant="primary">
          비밀번호 변경
        </Button>
      </Form>
    </Container>
  );
};

export default ChangePassword;
