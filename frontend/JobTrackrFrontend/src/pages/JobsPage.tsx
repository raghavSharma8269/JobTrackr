import SearchBarComponent from "../components/SearchBarComponent";
import JobCardComponent from "../components/JobCardComponent";
import ExpandedJobCard from "../components/ExpandedJobCard";
import JobListComponent from "../components/JobListComponent";


const JobsPage = () => {
  return (
    <div className="container-fluid vh-100 d-flex align-items-center">
      <div className="row w-100">
        <div
          className="col-md-10 d-flex justify-content-center align-items-start"
          style={{ marginTop: "350px" }}
        >
          <JobListComponent />
        </div>
        <div
          className="col-md-1 d-flex justify-content-center align-items-start"
          style={{ marginTop: "350px" }}
        >
          <ExpandedJobCard isVisible={true} />
        </div>
      </div>
    </div>
  );
};

export default JobsPage;
