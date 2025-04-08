import ExpandedJobCard from "../components/ExpandedJobCard";
import JobListComponent from "../components/JobListComponent";
import NavBarComponent from "../components/NavBarComponent.tsx";
import { useState } from "react";

type JobStatus = "applied" | "interview" | "accepted" | "rejected" | "none";

const JobsPage = () => {
  const [selectedJobIndex, setSelectedJobIndex] = useState<number | null>(null);
  const [favoriteJobs, setFavoriteJobs] = useState<boolean[]>(
    Array(10).fill(false),
  );
  const [jobStatuses, setJobStatuses] = useState<JobStatus[]>(
    Array(10).fill("none"),
  );

  const toggleFavorite = (index: number) => {
    const updated = [...favoriteJobs];
    updated[index] = !updated[index];
    setFavoriteJobs(updated);
  };

  const updateJobStatus = (index: number, newStatus: JobStatus) => {
    const updated = [...jobStatuses];
    updated[index] = newStatus;
    setJobStatuses(updated);
  };

  return (
    <div className="container-fluid min-vh-100 d-flex align-items-center">
      <div className="row w-100">
        <NavBarComponent />
        <div className="col-md-4 d-flex justify-content-center align-items-start overflow-hidden">
          <JobListComponent
            onJobClick={setSelectedJobIndex}
            favoriteJobs={favoriteJobs}
            toggleFavorite={toggleFavorite}
          />
        </div>
        <div className="col-md-8 d-flex justify-content-center align-items-start">
          <ExpandedJobCard
            isVisible={selectedJobIndex !== null}
            jobIndex={selectedJobIndex}
            isFavorite={
              selectedJobIndex !== null ? favoriteJobs[selectedJobIndex] : false
            }
            onToggleFavorite={() =>
              selectedJobIndex !== null && toggleFavorite(selectedJobIndex)
            }
            status={
              selectedJobIndex !== null ? jobStatuses[selectedJobIndex] : "none"
            }
            onUpdateStatus={(newStatus) =>
              selectedJobIndex !== null &&
              updateJobStatus(selectedJobIndex, newStatus)
            }
          />
        </div>
      </div>
    </div>
  );
};

export default JobsPage;
