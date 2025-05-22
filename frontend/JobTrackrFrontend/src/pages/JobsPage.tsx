import { useEffect, useState } from "react";
import ExpandedJobCard from "../components/ExpandedJobCard";
import JobListComponent from "../components/JobListComponent";
import NavBarComponent from "../components/NavBarComponent";
import axios from "axios";
import { Job, JobStatus } from "../types/Job";
import { JobContext } from "../context/JobContext.tsx";

const JobsPage = () => {
  //changes tab name
  useEffect(() => {
    document.title = "Jobs | JobVault";
  }, []);

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

      const normalizedJobs = response.data.map((job: Job) => ({
        ...job,
        applicationStatus: job.applicationStatus
          ? job.applicationStatus.toLowerCase()
          : "none",
      }));

      setJobs(normalizedJobs);
    } catch (err) {
      console.error("Failed to fetch jobs:", err);
    }
  };

  useEffect(() => {
    fetchJobs();
  }, []);

  const toggleFavorite = async (index: number) => {
    const job = jobs[index];
    const newFavorite = !job.favorite;

    try {
      const token = localStorage.getItem("token");
      await axios.put(
        `http://localhost:8080/api/jobs/favorite/${job.id}`,
        null,
        {
          params: { favorite: newFavorite },
          headers: {
            Authorization: `Bearer ${token}`,
          },
        },
      );
      setJobs((prev) =>
        prev.map((j, i) => (i === index ? { ...j, favorite: newFavorite } : j)),
      );
    } catch (error) {
      console.error("Failed to update favorite status:", error);
    }
  };

  const updateJobStatus = async (index: number, newStatus: JobStatus) => {
    const job = jobs[index];

    try {
      const token = localStorage.getItem("token");

      await axios.put(`http://localhost:8080/api/jobs/status/${job.id}`, null, {
        params: newStatus === "none" ? {} : { status: newStatus.toUpperCase() },
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      setJobs((prev) =>
        prev.map((j, i) =>
          i === index ? { ...j, applicationStatus: newStatus } : j,
        ),
      );
    } catch (error) {
      console.error("Failed to update job status:", error);
    }
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
              job={
                selectedJobIndex !== null ? { ...jobs[selectedJobIndex] } : null
              }
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
