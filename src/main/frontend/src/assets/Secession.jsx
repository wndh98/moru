import ToggleButton from 'react-bootstrap/ToggleButton';
import ToggleButtonGroup from 'react-bootstrap/ToggleButtonGroup';

function Secession() {
  return (
    <>
정말 탈퇴하시겠어요?

탈퇴버튼 선택 시, 계정은 삭제되며 복구되지 않습니다.

      <ToggleButtonGroup type="checkbox" defaultValue={[1, 3]} className="mb-2" variant="primary">
        <ToggleButton id="tbg-check-1" value={1}>
          탈퇴 (pre-checked)
        </ToggleButton>
 <ToggleButton id="tbg-check-1" value={1} variant="secondary">
          취소 (pre-checked)
        </ToggleButton>
      </ToggleButtonGroup>
    </>
  );
}

export default Secession;