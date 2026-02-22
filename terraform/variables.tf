variable vpc_cidr_block {
    default = "10.0.0.0/16"
}
variable subnet_cidr_block {
    default = "10.0.10.0/16"
}
variable avail_zone {
    default = "eu-west-1a"
}
variable env_prefix {
    default = "dev"
}
variable my_ip {
    default = "46.7.130.106/32"
}

variable jenkins_ip {
    default = "157.245.34.174/32"
}
variable instance_type {
    default = "t3.small"
}