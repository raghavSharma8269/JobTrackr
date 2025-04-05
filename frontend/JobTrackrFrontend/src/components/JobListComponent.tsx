import JobCardComponent from "./JobCardComponent";


const JobListComponent = () => {
    return (

        <div
            style={{
                height: "75vh",
                overflowY: "auto",
                overflowX: "hidden",
                marginTop:"100px"
            }}
            className="w-100 custom-scrollbar"
        >
            <div style={{ minHeight: "100%" }}>
                {[...Array(10)].map((_, index) => (
                    <div key={index} style={{ marginBottom: "10px" }}>
                        <JobCardComponent />
                    </div>
                ))}
            </div>
        </div>

    );
};



export default JobListComponent
