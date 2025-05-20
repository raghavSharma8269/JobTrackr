export interface Job {
  id: string;
  companyName: string;
  jobTitle: string;
  jobLocation: string;
  jobSalary: string;
  jobDescription: string;
  applicationStatus: "applied" | "interview" | "accepted" | "rejected" | "none";
  jobUrl: string;
  favorite: boolean;
  localDateTime: string;
}
