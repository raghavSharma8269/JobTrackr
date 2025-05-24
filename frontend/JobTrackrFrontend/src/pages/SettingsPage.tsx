import React, { useEffect } from "react";
import axios from "axios";
import { useUser } from "../context/UserContext";

const SettingsPage: React.FC = () => {
  const { user, loading: userLoading, refreshUser } = useUser();

  console.log("User from context:", user);

  useEffect(() => {
    document.title = "Settings | JobVault";
  }, []);

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
      await refreshUser(); // refresh filenames after upload
      alert("✅ " + response.data);
    } catch (error: any) {
      console.error("Resume upload failed:", error);
      alert(
        "❌ Upload failed: " +
          JSON.stringify(error.response?.data || error.message),
      );
    }
  };

  const uploadCv = async (file: File) => {
    const token = localStorage.getItem("token");
    const formData = new FormData();
    formData.append("coverLetterFile", file);

    try {
      const response = await axios.post(
        "http://localhost:8080/api/profile/cv",
        formData,
        {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "multipart/form-data",
          },
        },
      );
      await refreshUser(); // refresh filenames after upload
      alert("✅ " + response.data);
    } catch (error: any) {
      console.error("Cover letter upload failed:", error);
      alert(
        "❌ Upload failed: " +
          JSON.stringify(error.response?.data || error.message),
      );
    }
  };

  const handleResumeUpload = (event: React.ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0];
    if (file && file.type === "application/pdf") {
      uploadResume(file);
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
      uploadCv(file);
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

        {!userLoading && user?.resumeFileName ? (
          <p style={{ color: "#4caf50" }}>✅ {user.resumeFileName} uploaded</p>
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

        {!userLoading && user?.coverLetterFileName ? (
          <p style={{ color: "#4caf50" }}>
            ✅ {user.coverLetterFileName} uploaded
          </p>
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
