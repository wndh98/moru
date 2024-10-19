import React from 'react';
import { Form, Button, Container, Row, Col } from 'react-bootstrap';

class UserInfo extends React.Component {
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event) {
        event.preventDefault();
        // 폼 제출 로직을 여기서 처리하세요
    }

    render() {
        return (
            <Container>
                <Form onSubmit={this.handleSubmit}>
                    <Form.Group as={Row} className="mb-3" controlId="id">
                        <Form.Label column sm={2}>
                            아이디
                        </Form.Label>
                        <Col sm={10}>
                            <Form.Control type="text" placeholder="Id" />
                        </Col>
                    </Form.Group>

                    <Form.Group as={Row} className="mb-3">
                        <Col sm={{ span: 10, offset: 2 }}>
                            <Button type="button">아이디 중복 확인</Button>
                        </Col>
                    </Form.Group>

                    <Form.Group as={Row} className="mb-3" controlId="Password">
                        <Form.Label column sm={2}>
                            비밀번호
                        </Form.Label>
                        <Col sm={10}>
                            <Form.Control type="password" placeholder="비밀번호" />
                        </Col>
                    </Form.Group>

                    <Form.Group as={Row} className="mb-3">
                        <Col sm={{ span: 10, offset: 2 }}>
                            <Button type="button">비밀번호 변경</Button>
                        </Col>
                    </Form.Group>

                    <Form.Group as={Row} className="mb-3" controlId="Email">
                        <Form.Label column sm={2}>
                            이메일
                        </Form.Label>
                        <Col sm={10}>
                            <Form.Control type="email" placeholder="이메일" />
                        </Col>
                    </Form.Group>

                    <Form.Group as={Row} className="mb-3">
                        <Col sm={{ span: 10, offset: 2 }}>
                            <Button type="button">이메일 변경</Button>
                        </Col>
                    </Form.Group>

                    <Form.Group as={Row} className="mb-3" controlId="Nickname">
                        <Form.Label column sm={2}>
                            닉네임
                        </Form.Label>
                        <Col sm={10}>
                            <Form.Control type="text" placeholder="닉네임" />
                        </Col>
                    </Form.Group>

                    <Form.Group as={Row} className="mb-3">
                        <Col sm={{ span: 10, offset: 2 }}>
                            <Button type="button">닉네임 변경</Button>
                        </Col>
                    </Form.Group>

                    <Form.Group as={Row} className="mb-3" controlId="age">
                        <Form.Label column sm={2}>
                            나이
                        </Form.Label>
                        <Col sm={10}>
                            <Form.Control type="number" placeholder="나이" />
                        </Col>
                    </Form.Group>

                    <fieldset>
                        <Form.Group as={Row} className="mb-3">
                            <Form.Label as="legend" column sm={2}>
                                성별
                            </Form.Label>
                            <Col sm={10}>
                                <Form.Check
                                    type="radio"
                                    label="남성"
                                    name="gender"
                                    id="male"
                                />
                                <Form.Check
                                    type="radio"
                                    label="여성"
                                    name="gender"
                                    id="female"
                                />
                            </Col>
                        </Form.Group>
                    </fieldset>

                    <Form.Group as={Row} className="mb-3" controlId="height">
                        <Form.Label column sm={2}>
                            키
                        </Form.Label>
                        <Col sm={10}>
                            <Form.Control type="number" placeholder="키" />
                        </Col>
                    </Form.Group>

                    <Form.Group as={Row} className="mb-3">
                        <Col sm={{ span: 10, offset: 2 }}>
                            <Button type="submit">확인</Button>
                        </Col>
                    </Form.Group>
                </Form>
            </Container>
        );
    }
}

export default UserInfo;
