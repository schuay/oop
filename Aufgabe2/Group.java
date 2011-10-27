import java.util.Date;


public class Group extends BaseEnrollable{

	protected Group(Date enrollFrom, Date enrollTo, Date unenrollTo, int maxParticipants) 
	{
		/* TODO title */
		super("Group", enrollFrom, enrollTo, unenrollTo, maxParticipants);
		
	}
	
}
