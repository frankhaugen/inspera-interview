public class Candidate
{
     int UID;
     int ID;
     String Title;
     String Name;
     int ExtraTime;
     String StartTime;
     String EndTime;
     
     public Candidate(int UID, int ID, String Title, String Name, int ExtraTime, String StartTime, String EndTime)
     {
	 this.UID = UID;
	 this.ID = ID;
	 this.Title = Title;
	 this.Name = Name;
	 this.ExtraTime = ExtraTime;
	 this.StartTime = StartTime;
	 this.EndTime = EndTime;
     }
     
     public int GetUID()
     {
	  return UID;
     }
     
     public int GetID()
     {
	  return ID;
     }
     
     public String GetTitle()
     {
	  return Title;
     }
     
     public String GetName()
     {
	  return Name;
     }
     
     public int GetRxtraTime()
     {
	  return ExtraTime;
     }
     
     public String GetStartTime()
     {
	  return StartTime;
     }
     
     public String GetEndTime()
     {
	  return EndTime;
     }
}
