import React from 'react';
import { Form, Button, Container } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';

interface LoginProps {
    // 필요한 props를 여기에 정의하세요
}

class Login extends React.Component<LoginProps> {
    constructor(props: LoginProps) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event: React.FormEvent<HTMLFormElement>) {
        event.preventDefault();
        // 여기서 폼 제출 로직을 처리하세요
    }

    render() {
        return (
            <Container className="d-flex align-items-center justify-content-center" 
                style={{ minHeight: '100vh' }}>
                <div className="border border-primary border-3 rounded-3 p-5">
                    <p className="text-center"> 모르는 개 산책 </p>
                    <Form onSubmit={this.handleSubmit}>
                        <Form.Group className="mb-2" controlId="formBasicEmail">
                            <Form.Label>ID</Form.Label>
                            <Form.Control type="text" name='id' placeholder="ID" />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formBasicPassword">
                            <Form.Label>Password</Form.Label>
                            <Form.Control type="password" name="pw" placeholder="password"/>
                        </Form.Group>
                        
                        <div className="d-grid gap-2">
                            <Button type="submit" variant="primary">
                                로그인
                            </Button>
                            <Button variant="primary">
                                회원가입
                            </Button>
                            <Button variant="warning">
                                카카오 로그인
                            </Button>
                        </div>
                    </Form>
                </div>
            </Container>
        );
    }
}

export default Login;
