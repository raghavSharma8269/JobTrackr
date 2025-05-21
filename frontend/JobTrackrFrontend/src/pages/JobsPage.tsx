import { useEffect, useState } from "react";
import ExpandedJobCard from "../components/ExpandedJobCard";
import JobListComponent from "../components/JobListComponent";
import NavBarComponent from "../components/NavBarComponent";
import axios from "axios";
import { Job, JobStatus } from "../types/Job";
import { JobContext } from "../context/JobContext.tsx";

const JobsPage = () => {
  const [jobs, setJobs] = useState<Job[]>([]);
  const [selectedJobIndex, setSelectedJobIndex] = useState<number | null>(null);

  // Fetch jobs
  const fetchJobs = async () => {
    try {
      const token = localStorage.getItem("token");
      const response = await axios.get("http://localhost:8080/api/jobs", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      setJobs(response.data);
    } catch (err) {
      console.error("Failed to fetch jobs:", err);
    }
  };

  useEffect(() => {
    fetchJobs();
  }, []);

  const toggleFavorite = (index: number) => {
    setJobs((prev) =>
      prev.map((job, i) =>
        i === index ? { ...job, favorite: !job.favorite } : job,
      ),
    );
  };

  const updateJobStatus = (index: number, newStatus: JobStatus) => {
    setJobs((prev) =>
      prev.map((job, i) =>
        i === index ? { ...job, applicationStatus: newStatus } : job,
      ),
    );
  };

  return (
    <JobContext.Provider value={{ refreshJobs: fetchJobs }}>
      <div className="container-fluid min-vh-100 d-flex align-items-center">
        <div className="row w-100">
          <NavBarComponent />
          <div className="col-md-4 d-flex justify-content-center align-items-start overflow-hidden">
            <JobListComponent
              jobs={jobs}
              onJobClick={setSelectedJobIndex}
              toggleFavorite={toggleFavorite}
            />
          </div>
          <div className="col-md-8 d-flex justify-content-center align-items-start">
            <ExpandedJobCard
              isVisible={selectedJobIndex !== null}
              job={selectedJobIndex !== null ? jobs[selectedJobIndex] : null}
              onToggleFavorite={() =>
                selectedJobIndex !== null && toggleFavorite(selectedJobIndex)
              }
              onUpdateStatus={(newStatus) =>
                selectedJobIndex !== null &&
                updateJobStatus(selectedJobIndex, newStatus)
              }
            />
          </div>
        </div>
      </div>
    </JobContext.Provider>
  );
};

export default JobsPage;
