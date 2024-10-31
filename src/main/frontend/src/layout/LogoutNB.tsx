import React from 'react';
import { Navbar, Nav, Button, Container } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useNavigate } from 'react-router-dom';

const LogoutNB = () => {
  const navigate = useNavigate();
  const naviLoginPage = () => {

    navigate("/login");
  };

  const handleJoin = () => {
    navigate('/join')
  }

  return (
    <Navbar bg="light" expand="lg">
      <Container>
        <Navbar.Brand href="/">모르는 개 산책</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link href="/">Home</Nav.Link>
          </Nav>
          <div>
            <Button variant="primary" onClick={naviLoginPage} className="me-2">
              로그인
            </Button>
            <Button variant="primary" onClick={handleJoin}>
              회원가입
            </Button>
          </div>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default LogoutNB;
