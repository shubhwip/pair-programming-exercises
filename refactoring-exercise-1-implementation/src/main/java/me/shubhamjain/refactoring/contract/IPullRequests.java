package me.shubhamjain.refactoring.contract;

import java.util.List;

public interface IPullRequests {

	/**
	 * @return returns the top contributors
	 */
	public List<String> getTopContributors();

}
