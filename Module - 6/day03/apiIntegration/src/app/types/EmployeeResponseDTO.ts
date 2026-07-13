import ProjectSummaryDTO from "./ProjectSummaryDTO";

export default interface EmployeeResponseDTO {
  id: number;
  name: string;
  dept: string;
  projects: ProjectSummaryDTO[];
}