export interface User {
    id: number;
    username: string;
    points: number;
    banned: boolean;
  }

  export interface UserCredentials {
    username: string;
    password: string;
  }
