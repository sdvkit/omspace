package com.sdv.kit.omspace.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.sdv.kit.omspace.domain.model.UserData

class DropboxUserData(
    @SerializedName("account_id")
    val accountId: String,
    @SerializedName("account_type")
    val accountType: AccountType,
    val country: String,
    val disabled: Boolean,
    val email: String,
    @SerializedName("email_verified")
    val emailVerified: Boolean,
    @SerializedName("is_paired")
    val isPaired: Boolean,
    val locale: String,
    val name: Name,
    @SerializedName("referral_link")
    val referralLink: String,
    @SerializedName("root_info")
    val rootInfo: RootInfo,
    val team: Team,
    @SerializedName("team_member_id")
    val teamMemberId: String
) : UserData(
    userId = accountId,
    username = name.displayName,
    profilePictureUrl = null
)

data class AccountType(
    @SerializedName("tag")
    val tag: String
)

data class Name(
    @SerializedName("abbreviated_name")
    val abbreviatedName: String,
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("familiar_name")
    val familiarName: String,
    @SerializedName("given_name")
    val givenName: String,
    val surname: String
)

data class RootInfo(
    @SerializedName("tag")
    val tag: String,
    @SerializedName("home_namespace_id")
    val homeNamespaceId: String,
    @SerializedName("root_namespace_id")
    val rootNamespaceId: String
)

data class Team(
    val id: String,
    val name: String,
    @SerializedName("office_addin_policy")
    val officeAddinPolicy: OfficeAddinPolicy,
    @SerializedName("sharing_policies")
    val sharingPolicies: SharingPolicies
)

data class OfficeAddinPolicy(
    @SerializedName("tag")
    val tag: String
)

data class SharingPolicies(
    @SerializedName("default_link_expiration_days_policy")
    val defaultLinkExpirationDaysPolicy: LinkExpirationPolicy,
    @SerializedName("enforce_link_password_policy")
    val enforceLinkPasswordPolicy: LinkPasswordPolicy,
    @SerializedName("group_creation_policy")
    val groupCreationPolicy: GroupCreationPolicy,
    @SerializedName("shared_folder_join_policy")
    val sharedFolderJoinPolicy: FolderJoinPolicy,
    @SerializedName("shared_folder_link_restriction_policy")
    val sharedFolderLinkRestrictionPolicy: FolderLinkRestrictionPolicy,
    @SerializedName("shared_folder_member_policy")
    val sharedFolderMemberPolicy: FolderMemberPolicy,
    @SerializedName("shared_link_create_policy")
    val sharedLinkCreatePolicy: LinkCreatePolicy
)

data class LinkExpirationPolicy(
    @SerializedName("tag")
    val tag: String
)

data class LinkPasswordPolicy(
    @SerializedName("tag")
    val tag: String
)

data class GroupCreationPolicy(
    @SerializedName("tag")
    val tag: String
)

data class FolderJoinPolicy(
    @SerializedName("tag")
    val tag: String
)

data class FolderLinkRestrictionPolicy(
    @SerializedName("tag")
    val tag: String
)

data class FolderMemberPolicy(
    @SerializedName("tag")
    val tag: String
)

data class LinkCreatePolicy(
    @SerializedName("tag")
    val tag: String
)