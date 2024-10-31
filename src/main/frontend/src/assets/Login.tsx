
import React, { useState } from 'react';
import { Form, Button, Container } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import useAuthStore from '../lib/useAuthStore.tsx';

const requestLogin = async ({ uiId, uiPassword }) => {
    const response = await axios.post('http://localhost:8080/login', {
        uiId,
        uiPassword,
    },
        { withCredentials: true });

    return response;
};

const Login = () => {
    const navigate = useNavigate();
    const [uiId, setUiId] = useState('');
    const [uiPassword, setUiPassword] = useState('');
    const { login } = useAuthStore();

    const handleKakaoLogin = () => {
        const Rest_api_key = '687d5c4c207722cbbce3197113e5d9ca'
        const redirect_uri = 'http://localhost:80/auth/code'
        const kakaoURL = `https://kauth.kakao.com/oauth/authorize?client_id=${Rest_api_key}&redirect_uri=${redirect_uri}&response_type=code`
        window.location.href = kakaoURL
        const code = new URL(window.location.href).searchParams.get("code");
    }

    const handleLogin = async () => {
        try {
            const response = await requestLogin({ uiId, uiPassword });
            const accessToken = response.headers['accesstoken'];
            console.log(response)
            if (accessToken) {
                localStorage.setItem('accesstoken', accessToken);
                login();
                navigate('/mainLogin', { replace: true });
            } else {
                alert('로그인 실패: 유효하지 않은 접근 토큰입니다.');
            }
        } catch (error) {
            console.error('로그인 중 오류 발생:', error);
            alert('로그인 중 문제가 발생했습니다. 다시 시도해 주세요.');
        }
    };

    const handleJoin = () => {
        navigate('/join')
    }

    return (
        <Container
            className="d-flex align-items-center justify-content-center"
            style={{ minHeight: '100vh' }}
        >
            <div className="border border-primary border-3 rounded-3 p-5">
                <p className="text-center">모르는 개 산책</p>

                <Form.Group className="mb-2" controlId="formBasicEmail">
                    <Form.Label>ID</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="ID"
                        value={uiId}
                        onChange={(e) => setUiId(e.target.value)}
                    />
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control
                        type="password"
                        placeholder="password"
                        value={uiPassword}
                        onChange={(e) => setUiPassword(e.target.value)}
                    />
                </Form.Group>

                <div className="d-grid gap-2">
                    <Button type="button" onClick={handleLogin} variant="primary">
                        로그인
                    </Button>
                    <Button onClick={handleJoin} variant="primary">회원가입</Button>
                    <Button onClick={handleKakaoLogin} variant="warning">카카오 로그인</Button>
                </div>
            </div>
        </Container>
    );
};

export default Login;
