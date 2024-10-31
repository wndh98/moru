import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import CloseButton from 'react-bootstrap/CloseButton';

const SidebarContainer = styled.div`
  width: 250px;
  height: 100%;
  position: fixed;
  top: 0px;
  left: -250px;
  z-index: 10;
  border: 1px solid #c9c9c9;
  background-color: white;
  text-align: center;
  font-weight: bold;
  transition: all 0.2s ease;
  padding-top: 20px;
  padding-left: 0px;
  padding-right: 40px;

  &.open {
    left: 0px;
  }
`;

const MenuButton = styled.div`
  width: 50px;
  height: 50px;
  position: absolute;
  left: 0px;
  top: 0px;
  z-index: 1;
  background-image: url("https://upload.wikimedia.org/wikipedia/commons/b/b2/Hamburger_icon.svg");
  background-size: 50%;
  background-repeat: no-repeat;
  background-position: center;
  cursor: pointer;
`;


const PageCover = styled.div`
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0px;
  right: 0px;
  background-color: block;
  z-index: 4;
  display: none;

  &.open {
    display: block;
  }
`;

const Nav = styled.ul`
  margin: 0;
  padding: 0;
  list-style-type: none;
`;

const NavItem = styled.li`
  position: relative;
  width: 100%;

`;

const NavTitle = styled.li`
  display: block;
  width: 100%;
  line-height: 50px;
  text-indent: 20px;
  text-align: left;
  color: black;
  font-weight: bold;
  text-decoration: none;
  font-size: 20px;
  &:hover {
    background: #eee;
  }
`;
const NavLink = styled.a`
  display: block;
  width: 100%;
  line-height: 30px;
  text-indent: 20px;
  text-align: left;
  color: black;
  text-decoration: none;
  font-size:15px;
  padding:0px 0px 0px 20px;
`;

const Sidebar: React.FC = () => {
  const [isOpen, setIsOpen] = useState(false);


  const toggleSidebar = () => {
    setIsOpen((prev) => !prev);
  };


  useEffect(() => {
    if (isOpen) {
      document.body.style.overflow = 'hidden'; // Prevent scrolling
    } else {
      document.body.style.overflow = 'auto'; // Allow scrolling
    }
  }, [isOpen]);

  return (
    <>
      <MenuButton onClick={toggleSidebar} />
      <PageCover className={isOpen ? 'open' : ''} onClick={toggleSidebar} />
      <SidebarContainer className={isOpen ? 'open' : ''}>
      <CloseButton onClick={toggleSidebar} />
        <Nav>
          <NavItem>
            <NavTitle>회원</NavTitle>
            <NavLink href="/detailUserInfo">내 정보 보기</NavLink>
            <NavLink href="/UserWeihgt">체중 관리</NavLink>
            <NavLink href="#">활동 관리</NavLink>
            <NavLink href="#">식단 관리</NavLink>
          </NavItem>
          <NavItem>
            <NavTitle>반려동물</NavTitle>
            <NavLink href="#">반려동물 관리</NavLink>
            <NavLink href="#">체중 관리</NavLink>
            <NavLink href="#">식단 관리</NavLink>
            <NavLink href="#">산책 기록</NavLink>
          </NavItem>
          <NavItem>
            <NavTitle>게시판</NavTitle>
            <NavLink href="#">공지 게시판</NavLink>
            <NavLink href="#">자유 게시판</NavLink>
            <NavLink href="#">건의 게시판</NavLink>
          </NavItem>
        </Nav>
      </SidebarContainer>
    </>
  );
};

export default Sidebar;
