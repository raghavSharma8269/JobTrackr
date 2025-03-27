import JobCardComponent from "./JobCardComponent";

const JobListComponent = () => {
  return (
    <div
      style={{
        height: "450px", // Fixed height for the scrollable container
        overflowY: "auto", // Enables scrolling if content overflows
        overflowX: "hidden", // Disables horizontal scrolling
        paddingRight: "10px",
        marginRight: "500px",
      }}
    >
      {/* Wrapper div for the content inside the scrollable container */}
      <div
        style={{
          minHeight: "100%", // Ensure the content fills the container
        }}
      >
        {[...Array(20)].map((_, index) => (
          <div key={index} style={{ marginBottom: "10px" }}>
            <JobCardComponent />
          </div>
        ))}
      </div>
    </div>
  );
};

export default JobListComponent;
