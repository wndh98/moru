import axios from "axios";
import Quill, { RangeStatic } from "quill";
import { useRef } from "react";
import ReactQuill from "react-quill";

interface EditorProps {
    type: string;
    num: number;
}

const MAX_FILE_SIZE = 1024 * 1024 * 10;
const editorFileUpload = async (fileInput: HTMLInputElement): Promise<string[] | undefined> => {
    if (fileInput.files == null) return;
    const fileList: FileList = fileInput.files;
    const formData = new FormData();
    for (let i = 0; i < fileList.length; i++) {
        const fileExtension = fileList[i].name.split(".").pop()?.toLowerCase();
        if (fileExtension == null) {
            alert("잘못된 파일입니다.");
            return;
        }
        if (!["gif", "jpg", "jpeg", "png", "bmp"].includes(fileExtension)) {
            alert("지원하지 않는 파일형식입니다.");
            return
        }
        const fileSize = fileList[i].size;
        if (fileSize > MAX_FILE_SIZE) {
            alert("업로드 가능한 용량은 10MB입니다.");
            return;
        }
        formData.append("bfFile", fileList[i]);
    }

    const response = await axios.post("/boardFile/free/1", formData);
    const bfRealFilenames: string[] = response.data;
    return bfRealFilenames;
}
const setEditorImage = (quillRef: React.MutableRefObject<ReactQuill | null>, type: string, num: number) => {
    if (quillRef.current === null) return;
    const fileInput: HTMLInputElement = document.createElement("input");
    fileInput.setAttribute("type", "file");
    fileInput.setAttribute('multiple', 'multiple')
    fileInput.accept = "image/*";
    fileInput.click();
    const quill: Quill = quillRef.current.getEditor();
    const range: RangeStatic | null = quill.getSelection();
    fileInput.addEventListener("change", async () => {
        const realFilenames: string[] | undefined = await editorFileUpload(fileInput);
        if (realFilenames === undefined) return;
        realFilenames.forEach((realFilename: string) => {
            if (range !== null)
                quill.insertEmbed(range.index, "image", `/upload/${type}/${num}/${realFilename}`)
        });
    });
}



const Editor: React.FC<EditorProps> = ({ type, num }) => {
    const quillRef: React.MutableRefObject<ReactQuill | null> = useRef<ReactQuill>(null);
    const modules = {
        toolbar: {
            container: [
                [{ header: [1, 2, 3, 4, 5, false] }],
                ["bold", { "color": [] }, "link", { "size": ["small", false, "large", "huge"] }, "underline"],
                ["image"],
            ],
            handlers: {
                image: () => { setEditorImage(quillRef, type, num) }
            }
        }
    };
    return (

        <ReactQuill
            modules={modules}
            ref={(element) => {
                if (element !== null) {
                    quillRef.current = element;
                }
            }}
        />

    )
}
export default Editor;