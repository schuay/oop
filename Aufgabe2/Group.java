import java.util.Date;

public class Group extends BaseEnrollable{
	protected Group(Date enrollFrom, Date enrollTo, Date unenrollTo, int maxParticipants) 
	{
		/* TODO title */
		super("Group", enrollFrom, enrollTo, unenrollTo, maxParticipants);
	}

}
/* vim: set noet ts=4 sw=4: */
