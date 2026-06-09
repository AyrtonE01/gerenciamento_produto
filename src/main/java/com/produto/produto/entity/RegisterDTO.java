package com.produto.produto.entity;

import com.produto.produto.enums.ProdRole;

public record RegisterDTO (String login, String password, ProdRole role) {
}
