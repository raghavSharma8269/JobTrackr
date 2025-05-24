import React, { useEffect, useState } from "react";
import axios from "axios";

const SettingsPage: React.FC = () => {
  useEffect(() => {
    document.title = "Settings | JobVault";
  }, []);

  const [resume, setResume] = useState<File | null>(null);
  const [coverLetter, setCoverLetter] = useState<File | null>(null);

  const uploadResume = async (file: File) => {
    const token = localStorage.getItem("token");
    const formData = new FormData();
    formData.append("resumeFile", file);

    try {
      const response = await axios.post(
        "http://localhost:8080/api/profile/resume",
        formData,
        {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "multipart/form-data",
          },
        },
      );
      alert("✅ " + response.data);
    } catch (error: any) {
      console.error("Upload failed:", error);
      alert("❌ Upload failed: " + (error.response?.data || error.message));
    }
  };

  const handleResumeUpload = (event: React.ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0];
    if (file && file.type === "application/pdf") {
      setResume(file);
      uploadResume(file); // auto-upload
    } else {
      alert("Please upload a PDF file.");
      event.target.value = "";
    }
  };

  const handleCoverLetterUpload = (
    event: React.ChangeEvent<HTMLInputElement>,
  ) => {
    const file = event.target.files?.[0];
    if (file && file.type === "application/pdf") {
      setCoverLetter(file);
      // Optional: make this auto-upload too if needed
    } else {
      alert("Please upload a PDF file.");
      event.target.value = "";
    }
  };

  return (
    <div className="container-fluid text-center">
      {/* Resume Section */}
      <div
        className="p-4"
        style={{
          backgroundColor: "#292b38",
          marginLeft: "40px",
          marginRight: "40px",
          borderRadius: "10px",
        }}
      >
        <h1 className="default-text-color">Resume</h1>

        {resume ? (
          <p style={{ color: "#c9c9c9" }}>Selected: {resume.name}</p>
        ) : (
          <p style={{ color: "#c9c9c9" }}>No resume uploaded</p>
        )}

        <label
          htmlFor="resume-upload"
          style={{
            display: "inline-block",
            padding: "10px 20px",
            backgroundColor: "#1c1d26",
            color: "#ffffff",
            cursor: "pointer",
            borderRadius: "5px",
            marginTop: "10px",
          }}
        >
          Choose PDF File
        </label>
        <input
          id="resume-upload"
          type="file"
          accept="application/pdf"
          className="form-control mt-2"
          onChange={handleResumeUpload}
          style={{ display: "none" }}
        />
      </div>

      {/* Spacer */}
      <div className="py-3" style={{ backgroundColor: "#1c1d26" }}></div>

      {/* Cover Letter Section */}
      <div
        className="p-4"
        style={{
          backgroundColor: "#292b38",
          marginLeft: "40px",
          marginRight: "40px",
          borderRadius: "10px",
        }}
      >
        <h1 className="default-text-color">Cover Letter</h1>

        {coverLetter ? (
          <p style={{ color: "#c9c9c9" }}>Selected: {coverLetter.name}</p>
        ) : (
          <p style={{ color: "#c9c9c9" }}>No cover letter uploaded</p>
        )}

        <label
          htmlFor="cover-letter-upload"
          style={{
            display: "inline-block",
            padding: "10px 20px",
            backgroundColor: "#1c1d26",
            color: "#ffffff",
            cursor: "pointer",
            borderRadius: "5px",
            marginTop: "10px",
          }}
        >
          Choose PDF File
        </label>
        <input
          id="cover-letter-upload"
          type="file"
          accept="application/pdf"
          className="form-control mt-2"
          onChange={handleCoverLetterUpload}
          style={{ display: "none" }}
        />
      </div>

      {/* Spacer */}
      <div className="py-3" style={{ backgroundColor: "#1c1d26" }}></div>

      {/* Change Password Section */}
      <div
        className="p-4"
        style={{
          backgroundColor: "#292b38",
          marginLeft: "40px",
          marginRight: "40px",
          borderRadius: "10px",
        }}
      >
        <h1 className="default-text-color">Change Password</h1>
        <form>
          <div className="mb-3 mt-4">
            <input
              type="password"
              className="form-control default-placeholder-color"
              placeholder="Current Password"
              style={{ backgroundColor: "#1c1d26", borderColor: "#1c1d26" }}
              required
            />
          </div>
          <div className="mb-3">
            <input
              type="password"
              className="form-control default-placeholder-color"
              placeholder="New Password"
              style={{ backgroundColor: "#1c1d26", borderColor: "#1c1d26" }}
              required
            />
          </div>
          <div className="mb-3 text-white">
            <input
              type="password"
              className="form-control default-placeholder-color"
              placeholder="Confirm New Password"
              style={{ backgroundColor: "#1c1d26", borderColor: "#1c1d26" }}
              required
            />
          </div>
          <button
            type="submit"
            className="btn btn-primary mt-2"
            style={{
              backgroundColor: "#1c1d26",
              borderColor: "#1c1d26",
            }}
          >
            Change Password
          </button>
        </form>
      </div>
    </div>
  );
};

export default SettingsPage;
