import { South } from "@mui/icons-material";
import axios from "axios";
import { useEffect, useRef } from "react";
import ReactQuill from "react-quill";
import Editor from "./components/Editor";
function Test() {
    // const modules = {
    //     toolbar: {
    //         container: [
    //             [{ header: [1, 2, 3, 4, 5, false] }],
    //             ["bold", { "color": [] }, "link", { "size": ["small", false, "large", "huge"] }, "underline"],
    //             ["image"],
    //         ],
    //         handlers: {
    //             image: setEditorImage
    //         }
    //     }
    // };
    // const MAX_FILE_SIZE = 1024 * 1024 * 10;
    
    // const quillRef = useRef<ReactQuill>();
    // function setEditorImage() {
    //     const fileInput = document.createElement("input");
    //     fileInput.setAttribute("type", "file");
    //     fileInput.setAttribute('multiple', 'multiple')
    //     fileInput.accept = "image/*";
    //     fileInput.click();
    //     fileInput.addEventListener("change", async function () {
    //         if (this.files == null) return;
    //         const fileList: FileList = this.files;
    //         const formData = new FormData();
    //         for (let i = 0; i < fileList.length; i++) {
    //             const fileExtension = fileList[i].name.split(".").pop()?.toLowerCase();
    //             if (fileExtension == null) {
    //                 alert("잘못된 파일입니다.");
    //                 return;
    //             }
    //             if (!["gif", "jpg", "jpeg", "png", "bmp"].includes(fileExtension)) {
    //                 alert("지원하지 않는 파일형식입니다.");
    //                 return
    //             }
    //             const fileSize = fileList[i].size;
    //             if (fileSize > MAX_FILE_SIZE) {
    //                 alert("업로드 가능한 용량은 10MB입니다.");
    //                 return;
    //             }
    //             formData.append("bfFile", fileList[i]);
    //         }
    
    
    //         const response = await axios.post("/boardFile/free/1", formData);
    //         const bfRealFilenames=response.data;
    //         const quill = quillRef.current?.getEditor();
    //         const range = quill?.getSelection();
    //         if(range!==null && range!== undefined){
    //             bfRealFilenames.forEach((bfRealFilename:string) => {
    //                 // quill?.insertEmbed(range.index,"image",`/upload/board/${boNum}/${bfRealFilename}`)    
    //             });
                
    //         }
    //     })
    // }

    return (
        // <div>
        //     <ReactQuill

        //         modules={modules}
        //         ref={(element) => {
        //             if (element !== null) {
        //                 quillRef.current = element;
        //             }
        //         }}
        //     />
        //     <button type="button">테스트</button>
        // </div>
        <Editor
        type="board"
        num={1}
        ></Editor>
    )
}
export default Test;