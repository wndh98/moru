import { South } from "@mui/icons-material";
import axios from "axios";
import { useEffect } from "react";

function Test(){
    const test = async()=>{
        const response = await axios.post("/board",{boardBaseDTO:""});
        console.log(response);
    }

    useEffect(()=>{
        test();
        
    },[]);

    return(
        <div></div>
    )
}
export default Test;