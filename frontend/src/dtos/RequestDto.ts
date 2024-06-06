export class RequestDto {
  constructor(
    public currency: string,
    public name: string,
    public date: Date,
    public value: number
  ) {}
}
