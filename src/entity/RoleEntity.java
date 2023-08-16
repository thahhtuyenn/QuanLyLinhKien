package entity;

import java.util.Objects;

public class RoleEntity {
	private int roleId;
	private String roleName;
	private String description;

	public RoleEntity() {
	}

	public RoleEntity(int roleId, String roleName, String description) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.description = description;
	}

	public RoleEntity(String roleName, String description) {
		this.roleName = roleName;
		this.description = description;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return this.roleName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, roleId, roleName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleEntity other = (RoleEntity) obj;
		return Objects.equals(description, other.description) && roleId == other.roleId
				&& Objects.equals(roleName, other.roleName);
	}
	

}
