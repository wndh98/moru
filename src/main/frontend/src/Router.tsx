import React, { ReactNode, useEffect } from 'react';
import { BrowserRouter, Routes, Route, Navigate, useNavigate } from 'react-router-dom';
import MainLogin from './assets/MainLogin.tsx';
import Login from './assets/Login.tsx';
import Join from './assets/Join.tsx';
import Main from './assets/Main.tsx';
import DetailUserInfo from './assets/DetailUserInfo.tsx';
import useAuthStore from './lib/useAuthStore.tsx';
import ChangePassword from './assets/ChangePassword.tsx';
import UserWeight from './assets/UserWeight.tsx';

interface ProtectedRouteProps {
  children: ReactNode;
}

const ProtectedRoute: React.FC<ProtectedRouteProps> = ({ children }) => {
  const { isLoggedIn } = useAuthStore();
  const navigate = useNavigate();

  useEffect(() => {
    if (!isLoggedIn) {
      navigate("/login");
    }
  }, [isLoggedIn, navigate]);

  return <>{children}</>;
};

const Router = () => {
  const { isLoggedIn } = useAuthStore();

  return (
    <BrowserRouter>
      <Routes>
        {/* 로그인 안 해도 접근 가능한 URL */}
        <Route path="/login" element={<Login />} />
        <Route path="/join" element={<Join />} />
        <Route path="/main" element={<Main />} />
        {/* 로그인해야 접근 가능한 URL */}
        <Route element={<ProtectedRoute children={undefined} />}>
          <Route path="/mainLogin" element={<MainLogin />} />
          <Route path="/userInfo" element={<DetailUserInfo />} />
          <Route path="/userInfo/password" element={<ChangePassword />} />
          <Route path="/userWeight" element={<UserWeight />} />
        </Route>

        {/* 모든 다른 경로에 대한 리다이렉트 */}
        <Route path="/*" element={<Navigate replace to={isLoggedIn ? "/mainLogin" : "/login"} />} />
      </Routes>
    </BrowserRouter>
  );
};

export default Router;
