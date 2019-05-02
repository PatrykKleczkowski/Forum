export interface TopicPaginationDto {
    id: number;
    displayed: number;
    pinned: boolean;
    answers: number;
    title: string;
    topicAuthor: string;
    postAuthor: string;
    postCreatedDate: Date;
    topicCreatedDate: Date;
}