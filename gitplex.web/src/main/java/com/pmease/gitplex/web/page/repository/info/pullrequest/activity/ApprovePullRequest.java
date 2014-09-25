package com.pmease.gitplex.web.page.repository.info.pullrequest.activity;

import java.util.Date;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.LoadableDetachableModel;

import com.pmease.commons.hibernate.dao.Dao;
import com.pmease.gitplex.core.GitPlex;
import com.pmease.gitplex.core.model.PullRequest;
import com.pmease.gitplex.core.model.User;

@SuppressWarnings("serial")
public class ApprovePullRequest implements PullRequestActivity {

	private final Long requestId;
	
	private final Long userId;
	
	private final Date date;
	
	public ApprovePullRequest(PullRequest request, User user, Date date) {
		this.requestId = request.getId();
		this.userId = user.getId();
		this.date = date;
	}
	
	@Override
	public Panel render(String panelId) {
		return new ApproveActivityPanel(panelId, new LoadableDetachableModel<PullRequest>() {

			@Override
			protected PullRequest load() {
				return GitPlex.getInstance(Dao.class).load(PullRequest.class, requestId);
			}
			
		}, new LoadableDetachableModel<User>() {

			@Override
			protected User load() {
				return getUser();
			}
			
		}, date);
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public User getUser() {
		return GitPlex.getInstance(Dao.class).load(User.class, userId);
	}

}