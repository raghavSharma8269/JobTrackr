import React from 'react'
import RefreshStatusIconComponent from "./RefreshStatusIconComponent.tsx";

const SetStatusDropdownComponent = () => {
    return (
        <div className="dropdown mb-4">
            <button className="btn btn-secondary dropdown-toggle text-start" type="button"
                    data-bs-toggle="dropdown"
                    aria-expanded="false">
                Set Status...
            </button>
            <ul className="dropdown-menu" style={{backgroundColor:"#292b38"}}>
                <li><a className="dropdown-item default-text-color" style={{backgroundColor:"blue", borderRadius:"10px"}} href="#">Applied</a></li>
                <li><a className="dropdown-item default-text-color" style={{backgroundColor:"green", borderRadius:"10px"}} href="#">Interview</a></li>
                <li><a className="dropdown-item default-text-color" style={{backgroundColor:"#7400f0", borderRadius:"10px"}} href="#">Accepted</a></li>
                <li><a className="dropdown-item default-text-color" style={{backgroundColor:"red", borderRadius:"10px"}} href="#">Rejected</a></li>
            </ul>
            <RefreshStatusIconComponent/>

        </div>    )
}
export default SetStatusDropdownComponent
