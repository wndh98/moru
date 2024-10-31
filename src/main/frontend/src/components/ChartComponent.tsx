import React, { useEffect, useCallback, useState } from 'react';
import { useForm } from 'react-hook-form';
import { BarChart, Bar, XAxis, YAxis, Tooltip } from 'recharts';
import Button from 'react-bootstrap/Button';
import axios from 'axios';

const dataWeight = [
  { name: 'uwDate', uwWeight: 70 },
];

const dataMuscleMass = [
  { name: 'User 1', uwMuscle: 30 },
];

const dataBodyFat = [
  { name: 'User 1', uwBodyFat: 10 },
];

const ChartComponent: React.FC = () => {
  const accessToken = localStorage.getItem("accesstoken");
  const { register, setValue } = useForm();
  const fetchUserData = useCallback(async () => {
    try {
      const response = await axios.get('http://localhost:8080/userWeight', {
        headers: {
          "Content-Type": "application/json",
          "accesstoken": accessToken,
        }
      });
      const userData = response.data;
      setValue('uwDate', userData.uwDate);
      setValue('uwWeight', userData.uwWeight);
      setValue('uwBodyFat', userData.uwBodyFat);
      setValue('uwMuscle', userData.uwMuscle);
    } catch (error) {
      console.error('사용자 정보를 가져오는 중 오류 발생:', error);
    }
  }, [accessToken, setValue]);

  const [chartType, setChartType] = useState<'uwWeight' | 'uwMuscle' | 'uwBodyFat'>('uwWeight');

  const renderChart = () => {
    if (chartType === 'uwWeight') {
      return (
        <BarChart width={600} height={300} data={dataWeight}>
          <XAxis dataKey="name" />
          <YAxis />
          <Tooltip />
          <Bar dataKey="uwWeight" fill="#8884d8" />
        </BarChart>
      );
    } else if (chartType === 'uwMuscle') {
      return (
        <BarChart width={600} height={300} data={dataMuscleMass}>
          <XAxis dataKey="name" />
          <YAxis />
          <Tooltip />
          <Bar dataKey="uwMuscle" fill="#82ca9d" />
        </BarChart>
      );
    } else {
      return (
        <BarChart width={600} height={300} data={dataBodyFat}>
          <XAxis dataKey="name" />
          <YAxis />
          <Tooltip />
          <Bar dataKey="uwBodyFat" fill="#3d5066" />
        </BarChart>
      );
    }
  };
  useEffect(() => {
    fetchUserData();
  }, [fetchUserData]);

  return (
    <div>
      <div>
        <Button variant="outline-secondary" {...register('uwWeight')}>체중</Button>{' '}
        <Button variant="outline-secondary" {...register('uwMuscle')}>골격근량</Button>{' '}
        <Button variant="outline-secondary" {...register('uwBodyFat')}>체지방</Button>{' '}
      </div>
      {renderChart()}
    </div>
  );
};

export default ChartComponent;
