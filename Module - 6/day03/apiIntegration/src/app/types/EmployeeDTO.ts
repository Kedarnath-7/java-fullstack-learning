import ProjectSummaryDTO from "./ProjectSummaryDTO";

export default interface EmployeeDTO {
  id: number;
  name: string;
  dept: string;
  projects: ProjectSummaryDTO[];
}