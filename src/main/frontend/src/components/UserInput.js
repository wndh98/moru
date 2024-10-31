import React from 'react';
import PropTypes from 'prop-types';

const UserInput = ({ type, placeholder, value, name }) => {
  return (
    <input
      className="user-input"
      type={type}
      placeholder={placeholder}
      value={value}
      name={name}
    />
  );
};

UserInput.propTypes = {
  type: PropTypes.string.isRequired,
  placeholder: PropTypes.string.isRequired,
  value: PropTypes.string.isRequired,
  name: PropTypes.string.isRequired,
};

export default UserInput;