
export default interface IAvionModel {
    id?: number | null,
    title : string,
    description : string,
    timeLimit : number,
    minimumPassingScore? : number | null,
    numberOfQuestions? : number | null,
    instructions? : string | null     
}