export interface User {
    id: number;
    username: string;
  points: number;
  banned: boolean;
  rank: string;
  }

  export interface UserCredentials {
    username: string;
    password: string;
  }
