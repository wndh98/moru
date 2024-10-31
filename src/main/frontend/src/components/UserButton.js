import React from 'react';
import PropTypes from 'prop-types';

const UserButton = ({ disabled, onClick, text }) => {
  return (
    <button className="user-button" disabled={disabled} onClick={onClick}>
      {text}
    </button>
  );
};

UserButton.propTypes = {
  disabled: PropTypes.bool.isRequired,
  onClick: PropTypes.func.isRequired,
  text: PropTypes.string.isRequired,
};

export default UserButton;